package mayton.probe.hibernate;

import mayton.probe.GeoIpCity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.ServerSocket;
import java.util.List;


public class CriteriaTest {

    @BeforeClass
    public void beforeClass(){

    }

    @Test
    public void test1(){
        Configuration configuration = new Configuration();
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.openSession();

        //Criteria criteria = session.createCriteria(GeoIpCity.class);
        //List<GeoIpCity> results = criteria.list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<GeoIpCity> criteriaQuery = builder.createQuery(GeoIpCity.class);

        Root<GeoIpCity> root = criteriaQuery.from(GeoIpCity.class);



    }

    @Test
    public void testNative(){
        Configuration configuration = new Configuration();
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.openSession();
        NativeQuery query = session.createNativeQuery("SELECT DUMMY FROM DUAL");
    }

}
