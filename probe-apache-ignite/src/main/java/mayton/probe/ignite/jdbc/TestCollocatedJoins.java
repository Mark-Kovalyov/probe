package mayton.probe.ignite.jdbc;

public class TestCollocatedJoins {

    public static void main(String[] args) {

        String create =
                "CREATE TABLE 'city' (" +
                " ID          INT(11), " +
                " Name        CHAR(35), " +
                " CountryCode CHAR(3)," +
                " Distinct    CHAR(20)," +
                " Population  INT(11)," +
                "     PRIMARY KEY(ID,CountryCode)" +
                ") WITH 'template=partitioned, " +
                        "backups=1," +
                        "affinityKey=CountryCode'";

        String sql=
                "SELECT ct.name, count(c.name) " +
                " FROM Country as ct JOIN City as c " +
                " ON c.countryCode = ct.code" +
                " WHERE ct.name IN ('Canada', 'India') GROUP BY (ct.name)";

        // 1. Initial Query
        // 2. Query execution over local data
        // 3. Reduce multiple results in one
    }

}
