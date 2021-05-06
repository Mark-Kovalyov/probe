package mayton;

import com.github.rholder.retry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SerializableTransactionCallable implements Callable<Boolean> {

    public static Logger logger = LoggerFactory.getLogger(LongLiveTransactions.class);

    private LongLiveTransactions longLiveTransactions;

    public SerializableTransactionCallable(LongLiveTransactions longLiveTransactions, int id1, int id2, int id3, BigDecimal interest) {
        this.longLiveTransactions = longLiveTransactions;
    }

    @Override
    public Boolean call() throws Exception {
        return false;
    }

    private boolean exhangeMoneyInSerializableTransaction(int id1, int id2, int id3, BigDecimal interest) {
        logger.info("Exchange money from {} to {} with percent of interest to {}", id1, id2, id3);
        try(PreparedStatement st = longLiveTransactions.connection.prepareStatement("SELECT id,balance FROM balances WHERE id IN (?,?,?)")) {
            logger.info("Check for source...");
            st.setInt(1, id1);
            st.setInt(2, id2);
            st.setInt(3, id3);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                if (rs.getInt(1) == id1) {
                    
                }
            }
            return true;
        } catch (SQLException ex) {
            logger.warn("exhangeMoneyInSerializableTransaction exception", ex);
            return false;
        }
    }
}
