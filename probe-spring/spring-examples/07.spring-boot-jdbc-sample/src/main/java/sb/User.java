package sb;

import java.util.Random;
import java.util.UUID;

public class User {

    public long id;
    public String name;
    public String alias;

    public User() {
        this.id = new Random().nextInt();
        this.name  = UUID.randomUUID().toString();
        this.alias = "Default alias";
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

}