package mayton.web.probespringredis;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    void save(User user);
    List<User> findAll();
    User findBy(String id);
    void update(User user);
    void delete(String id);

}
