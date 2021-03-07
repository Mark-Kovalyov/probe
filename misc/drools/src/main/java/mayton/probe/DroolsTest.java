package mayton.probe;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsTest {
    
    static Logger logger = LoggerFactory.getLogger(DroolsTest.class);

    public static void main(String[] args) {
            
            logger.info("Begin");
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
 
            // go !
            Account account = new Account(200);
            account.withdraw(150);
            kSession.insert(account);
            kSession.fireAllRules();
            
            logger.info("End");
        
    }
}
