package mayton.web.probespringredis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserResource {

    private UserRepository userRepository;

    @GetMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") String id, @PathVariable("name") String name) {
        userRepository.save(new User(id, name, 2000L));

    }

}
