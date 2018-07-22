package mayton.probe.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mayton.probe.Log4jUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QueryDSLTest {

    @BeforeClass
    public static void beforeClass(){
        Log4jUtils.init();
    }

    @Ignore
    @Test
    public void testEm(){

        // Persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf-pu");
        EntityManager em = emf.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        emf.close();
    }

    @Ignore
    @Test
    public void basicQuery() {


        //JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        /*
        List<Person> persons = queryFactory.selectFrom(person)
                .where(
                        person.firstName.eq("John"),
                        person.lastName.eq("Doe"))
                .fetch();
                */
    }

    @Ignore
    @Test
    public void order(){
        /*
        List<Person> persons = queryFactory.selectFrom(person)
                .orderBy(person.lastName.asc(),
                        person.firstName.desc())
                .fetch();
                */
    }

    @Ignore
    @Test
    public void subQueries(){
        /*
        List<Person> persons = queryFactory.selectFrom(person)
                .where(person.children.size().eq(
                        JPAExpressions.select(parent.children.size().max())
                                .from(parent)))
                .fetch();
                */
    }

    @Ignore
    @Test
    public void tupleProjection(){
        /*
        List<Tuple> tuples = queryFactory.select(
                person.lastName, person.firstName, person.yearOfBirth)
                .from(person)
                .fetch();
        */
    }
}
