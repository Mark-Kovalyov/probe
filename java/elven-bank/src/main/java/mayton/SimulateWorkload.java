package mayton;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SimulateWorkload {

    public static final int ROWS = 100;

    public static final int LIMIT_TIME = 120; // sec

    public static Logger logger = LoggerFactory.getLogger(SimulateWorkload.class);

    // create table elven.balances(
    //    id int PRIMARY KEY,
    //    elven_name TEXT NOT NULL,
    //    balance NUMERIC(12,2) NOT NULL CHECK(balance >= 0)
    //)

    public static void clean(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("delete from balances")) {
            boolean res = st.execute();
        }
    }

    public static void initTable(Connection connection) throws SQLException {
        Random random = new Random();
        try (PreparedStatement st = connection.prepareStatement("insert into balances(id, elven_name, balance) values(?, ?, ?)")) {
            for (int i = 0; i < ROWS; i++) {
                st.setInt(1, i);
                st.setString(2, "Elven" + i);
                st.setBigDecimal(3,
                        BigDecimal.valueOf(1000.0)
                        .add(BigDecimal.valueOf(1000.0 * random.nextGaussian()))
                        .abs());
                boolean res = st.execute();
            }
        }
        connection.commit();
    }

    public static void process() {
        Runnable[] runnable = new Runnable[10];
    }

    // select pid,application_name,state,query,query_start from pg_stat_activity;
    // SET search_path TO elven,public;
    public static void main(String[] args) throws ParseException, SQLException, InterruptedException {
        MDC.put("threadName","main");
        Random random = new Random();
        logger.info("Start");

        String jdbcUrl  = System.getenv("PG_DEF_JDBC_URL");
        String jdbcUser = System.getenv("PG_DEF_JDBC_USER");
        String jdbcPwd  = System.getenv("PG_DEF_JDBC_PWD");
        Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        connection.setSchema("elven");
        connection.setAutoCommit(false);
        clean(connection);
        initTable(connection);
        BigDecimal capBefore = capitalization(connection);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(
                    new LongLiveTransactions(
                            DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd),
                            Range.between(0, ROWS - 1),
                            LIMIT_TIME,
                            "LongLiveThread-" + i));
        }

        /*for (int i = 0; i < 20; i++) {
            executorService.execute(
                    new ShortLiveTransactions(
                            DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd),
                            Range.between(0, ROWS - 1),
                            LIMIT_TIME,
                            "ShortLiveThread-" + i));
        }*/

        logger.info("Waiting for executor service is finished");
        executorService.shutdown();
        boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);
        logger.info("Await termination result = {}", finished);

        BigDecimal capAfter = capitalization(connection);
        logger.info("Cap before = {}", capBefore);
        logger.info("Cap after = {}", capAfter);
        logger.info("Finish");
    }

    private static BigDecimal capitalization(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery("select sum(balance) from balances");
        res.next();
        return res.getBigDecimal(1);
    }

}
