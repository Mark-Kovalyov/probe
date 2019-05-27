package mayton.probe.ignite;

public class DistributedSqlExample {

    // $ sqlline.sh --color=true \
    //             --verbise=true \
    //             -u jdbc:ignite:thin://127.0.0.1/

    // CREATE TABLE CITY(ID LONG PRIMARY KEY, NAME VARCHAR) WITH "template = replicated"
    // CREATE TABLE                                         WITH "backups=1, affinityKey=city_id"

    public static void main(String[] args) {

    }

}
