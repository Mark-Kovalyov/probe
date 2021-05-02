package mayton;

import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

import static java.math.BigDecimal.ZERO;
import static mayton.Utils.sleepSec;

public class ShortLiveTransactions implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(ShortLiveTransactions.class);
    private String threadName;

    private Random random = new Random();
    private Connection connection;
    private Range<Integer> range;
    private LocalDateTime start;
    private int seconds;

    public ShortLiveTransactions(Connection connection, Range<Integer> range, int seconds, String threadName) {
        this.connection = connection;
        this.start = LocalDateTime.now();
        this.threadName = threadName;
        this.range = range;
        this.seconds = seconds;
    }

    // begin isolation level read committed;
    // select * from balances where id in (1,2) for update;

    void transferMoneyInReadCommitedTransaction(int id1, int id2, BigDecimal sum) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT id,balance FROM balances WHERE id IN (?,?) FOR UPDATE");
        st.setInt(1, id1);
        st.setInt(2, id2);
        ResultSet res = st.executeQuery();
        BigDecimal sourceBalance = null;
        BigDecimal destBalance = null;
        while(res.next()) {
            int id = res.getInt("id");
            BigDecimal balance = res.getBigDecimal("balance");
            logger.info(":: {} {}",id, balance.toPlainString());
            if (id == id1) {
                sourceBalance = balance;
            } else if (id == id2) {
                destBalance = balance;
            }
        }
        if (sourceBalance.subtract(sum).compareTo(ZERO) > 0) {
            logger.info("Approved! Transfering...");

            PreparedStatement pst = connection.prepareStatement("UPDATE balances SET balance = balance - ? WHERE id = ?");
            pst.setBigDecimal(1, sum);
            pst.setInt(2, id1);
            boolean res1 = pst.execute();
            pst.close();

            PreparedStatement pst2 = connection.prepareStatement("UPDATE balances SET balance = balance + ? WHERE id = ?");
            pst2.setBigDecimal(1, sum);
            pst2.setInt(2, id2);
            boolean res2 = pst2.execute();
            pst2.close();
            logger.info("Transfered OK");
        } else {
            logger.info("Declined. Not enough money!");
        }
        sleepSec(3 + random.nextInt(2));
        st.close();
    }

    void transferMoney(int id1, int id2, BigDecimal sum) throws SQLException {
        logger.info("Transer {}/{}/{}", id1, id2, sum);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        try{
            transferMoneyInReadCommitedTransaction(id1, id2, sum);
            connection.commit();
        } catch (Exception ex) {
            logger.warn("Rolled back transfer {}/{}/{}", id1,id2,sum);
            connection.rollback();
        }

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
                //double money = 1.0;//Math.abs(40.0 + 30.0 * random.nextGaussian());
                if (id1 != id2) {
                    //transferMoney(id1, id2, BigDecimal.valueOf(money).round(new MathContext(3, HALF_UP)));
                    transferMoney(id1, id2, BigDecimal.valueOf(40L + random.nextInt(30)));
                }
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
}
