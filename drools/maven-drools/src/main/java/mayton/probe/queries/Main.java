package mayton.probe.queries;

import mayton.probe.Account;
import mayton.probe.DroolsTest;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static Logger logger = LoggerFactory.getLogger(DroolsTest.class);

    public static void main(String[] args) {

        logger.info("Begin");
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        QueryResults results = kSession.getQueryResults( "people under the age of 21" );
        System.out.println( "we have " + results.size() + " people under the age of 21" );

        System.out.println( "These people are under the age of 21:" );

        for ( QueryResultsRow row : results ) {
            Person person = ( Person ) row.get( "person" );
            System.out.println( person.getName() + "\n" );
        }
    }

}
