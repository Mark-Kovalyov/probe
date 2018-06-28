package mayton.probes;

import com.google.common.graph.*;
import mayton.probes.oracle.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GuavaCommonGraphTest {

    @Test
    public void testReachable() {

        MutableNetwork<OracleObject, OracleObject> model
                = NetworkBuilder
                .directed() // Oriented
                .allowsSelfLoops(false)
                .build();

        OracleObject t1 = new OracleTable(1,"T1");
        OracleObject t2 = new OracleTable(2,"T2");
        OracleObject v1 = new OracleView("V1");
        OracleObject v2 = new OracleView("V2");

        model.addNode(t1);
        model.addNode(t2);
        model.addNode(v1);
        model.addNode(v2);

        OracleObject constr = new OracleConstraint(3,"FK1");

        model.addEdge(t1, t2, constr);
        model.addEdge(v1, t1, new ViewDependency());
        model.addEdge(v2, v1, new ViewDependency());


        assertTrue("hasEdgeConnecting(emp, dept)", model.hasEdgeConnecting(t1,t2));
        assertFalse("hasEdgeConnecting(dept, emp)", model.hasEdgeConnecting(t2,t1));

    }

}
