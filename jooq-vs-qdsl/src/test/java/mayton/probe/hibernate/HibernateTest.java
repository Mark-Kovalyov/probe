package mayton.probe.hibernate;

import mayton.probe.GeoIpCity;
import mayton.probe.GeoIpCityPk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class HibernateTest {

    static Logger logger = LoggerFactory.getLogger(HibernateTest.class); 
    
    private static SessionFactory factory;
    Session session = factory.openSession();

    @BeforeClass
    public static void beforeClass() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Before
    public void before(){

    }

    @Ignore
    @Test
    public void testAddGeoIpCity() {
        Session session = factory.openSession();

        Transaction tx = null;

        tx = session.beginTransaction();
        GeoIpCity GeoIpCity = new GeoIpCity(new GeoIpCityPk("1","2"),"Ukraine","Kievskaya","Kiev");
        GeoIpCityPk id = (GeoIpCityPk) session.save(GeoIpCity);
        tx.commit();

    }

    @Test
    public void testListGeoIpCitys() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        List GeoIpCitys = session.createQuery("FROM GeoIpCity").list();
        for (Iterator iterator = GeoIpCitys.iterator(); iterator.hasNext(); ) {
            GeoIpCity geoIpCity = (GeoIpCity) iterator.next();

            logger.info("First Name: {}" , geoIpCity.getCity());
        }
        tx.commit();
    }

    /* Method to UPDATE salary for an GeoIpCity */
    public void updateGeoIpCity(Integer GeoIpCityID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        GeoIpCity GeoIpCity = (GeoIpCity) session.get(GeoIpCity.class, GeoIpCityID);
        //GeoIpCity.setSalary( salary );
        session.update(GeoIpCity);
        tx.commit();

    }

    /* Method to DELETE an GeoIpCity from the records */
    public void deleteGeoIpCity(Integer GeoIpCityID) {
        Session session = factory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        GeoIpCity GeoIpCity = (GeoIpCity) session.get(GeoIpCity.class, GeoIpCityID);
        session.delete(GeoIpCity);
        tx.commit();
    }

}
