package mayton.probe.spring.probespringsecurity;

import java.util.HashSet;
import java.util.Set;

public class UserDb {

    private static Set<User> users;

    private UserDb(){
        users = new HashSet<>();
    }

    public static final UserDb userDb = new UserDb();

    public static boolean register(String username, int id) {
        return users.add(new User(username, id));
    }

}
