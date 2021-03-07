package mayton.probe.ignite.transactions;

import mayton.probe.ignite.entities.Account;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProbeTransactions {

    static Logger logger = LoggerFactory.getLogger(ProbeTransactions.class);

    public static void main(String[] args) {

        org.apache.ignite.internal.binary.BinaryMarshaller bm;

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {

            ClientCache<Long, Account> cache = igniteClient.getOrCreateCache("default");

            try(Transaction tx = Ignition.ignite().transactions().txStart(
                    TransactionConcurrency.PESSIMISTIC,
                    TransactionIsolation.REPEATABLE_READ)) {

                Account account1 = cache.get(0L);
                Account account2 = cache.get(1L);

                account1.update(account2.balance());

                tx.commit();

            }

        } catch (Exception ex) {
            logger.error(ex.toString());
        }

    }

}
