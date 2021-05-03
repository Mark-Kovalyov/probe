package mayton;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.Range;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LongLiveTransactions implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(LongLiveTransactions.class);
    private String threadName;

    private Random random = new Random();
    public Connection connection;
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
        logger.info("Start LongLiveTransactions thread");
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
                long waitMs = Utils.randomSleepSec(10, 20);
                logger.debug("Waiting random time = {} ms", waitMs);
            }
        } catch (SQLException throwables) {
            logger.error("SQLException[1]", throwables);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                logger.warn("SQLException[2]", throwables);
            }
        }
        logger.info("Finish LongLiveTransactions thread");
    }

    private void exhangeMoney(int id1, int id2, int id3, BigDecimal interest) throws SQLException {

        if (id1 == id2 || id2 == id3 || id1 == id3) {
            logger.warn("Ignored combination of {}/{}/{}", id1,id2,id3);
            return;
        }

        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(res -> !res)
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withWaitStrategy(WaitStrategies.randomWait(2000, TimeUnit.MILLISECONDS))
                .build();

        try {
            retryer.call(new SerializableTransactionCallable(this, id1, id2, id3, interest));
        } catch (RetryException e) {
            logger.warn("RetryException[3], NumberOfFailedAttempts = {}", e.getNumberOfFailedAttempts());
        } catch (ExecutionException e) {
            logger.warn("ExecutionException[4], message = {}", e.getMessage());
        }
    }


}
