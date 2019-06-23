package mayton.probe.jooq;

import org.junit.Test;

public class JooqTest {


    @Test
    public void testQueryingAfterMigration() throws Exception {
        /*
        try (Connection c = DriverManager.getConnection("jdbc:h2:~/flyway-test", "sa", "")) {
            Result<?> result =
                    DSL.using(c)
                            .select(
                                    AUTHOR.FIRST_NAME,
                                    AUTHOR.LAST_NAME,
                                    BOOK.ID,
                                    BOOK.TITLE
                            )
                            .from(AUTHOR)
                            .join(BOOK)
                            .on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                            .orderBy(BOOK.ID.asc())
                            .fetch();

            assertEquals(4, result.size());
            assertEquals(asList(1, 2, 3, 4), result.getValues(BOOK.ID));
        }*/
    }

    public void test1() {
        /*
        create.selectFrom(BOOK)
                .where(BOOK.PUBLISHED_IN.eq(2011))
                .orderBy(BOOK.TITLE)
                */
    }

    public void test2() {
        /*create.select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, count())
                .from(AUTHOR)
                .join(BOOK).on(AUTHOR.ID.equal(BOOK.AUTHOR_ID))
                .where(BOOK.LANGUAGE.eq("DE"))
                .and(BOOK.PUBLISHED.gt(date("2008-01-01")))
                .groupBy(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .having(count().gt(5))
                .orderBy(AUTHOR.LAST_NAME.asc().nullsFirst())
                .limit(2)
                .offset(1)
                */
    }

    public void predicates() {
        //select().from(t).where(t.a.eq(select(t2.x).from(t2));
        //select().from(t).where(t.a.eq(any(select(t2.x).from(t2)));
        //select().from(t).where(t.a.in(select(t2.x).from(t2));
    }

}
