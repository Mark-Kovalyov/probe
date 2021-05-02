package mayton;

import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

public class LongLiveTransactions implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(LongLiveTransactions.class);
    private String threadName;

    private Random random = new Random();
    private Connection connection;
    private Range<Integer> range;
    private LocalDateTime start;
    private int seconds;

    public LongLiveTransactions(Connection connection, Range<Integer> range, int seconds, String threadName) {
        this.connection = connection;
        this.start = LocalDateTime.now();
        this.threadName = threadName;
        this.range = range;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        MDC.put("threadName", threadName);
        int min = range.getMinimum();
        int size = range.getMaximum() - min;
        try {
            connection.setSchema("elven");
            connection.setAutoCommit(false);
            while (!start.plusSeconds(seconds).isBefore(LocalDateTime.now())) {
                int id1 = min + random.nextInt(size);
                int id2 = min + random.nextInt(size);
                int id3 = min + random.nextInt(size);
                exhangeMoney(id1, id2, id3, new BigDecimal("0.05"));
            }
        } catch (SQLException throwables) {
            logger.error("SQLException", throwables);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                logger.warn("SQLException", throwables);
            }
        }
    }

    private void exhangeMoney(int id1, int id2, int id3, BigDecimal interest) throws SQLException {
        if (id1 == id2 || id2 == id3 || id1 == id3) return;
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        try {
            exhangeMoneyInSerializableTransaction(id1, id2, id3, interest);
            connection.commit();
        } catch (Exception ex) {
            logger.warn("Rolled back");
            connection.rollback();
        }

    }

    private void exhangeMoneyInSerializableTransaction(int id1, int id2, int id3, BigDecimal interest) {

    }
}
