package sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository users;

    @RequestMapping("test")
    public String test() {
        log.info("Test");
        return "OK";
    }

    @RequestMapping("user")
    public User getUser(@RequestParam("id") long id) {
        log.info("Get user");
        //return users.getUser(id);
        return new User();
    }

    @RequestMapping("users")
    public List<User> getUsers(@RequestParam("ids") long[] ids) {
        log.info("Get users");
        //return users.getUsers(ids);
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        return list;
    }
}