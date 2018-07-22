package mayton.probe.jpa;

import mayton.probe.Log4jUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPAGeoTest {

    @BeforeClass
    public static void beforeClass() {
        Log4jUtils.init();
    }

    @Before
    public void before(){

    }

    @Test
    public void a_ping() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf-pu");
        EntityManager em = emf.createEntityManager();

    }

    @Test
    public void b_test() {
        // Persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf-pu");
        EntityManager em = emf.createEntityManager();

    }

}
