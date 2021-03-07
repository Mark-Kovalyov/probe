package mayton.probe.spring.probespringsecurity;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;

@Controller("/main")
public class MainController {

    @GetMapping("/userlist")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<User> userList() {

        return Collections.emptyList();
    }

    @PostMapping("register")
    @Secured("ROLE_ADMIN")
    public boolean registerUser(@RequestParam(name = "username") String username, @RequestParam(name = "id") int id) {
        UserDb.register(username, id);
        return true;
    }

    @RolesAllowed("ROLE_MTN")
    public void test() {

    }

    @PreAuthorize("hasRole('ROLE_SPITTER')")
    public void addSpittle(Spittle spittle) {

    }

    @PreAuthorize("( hasRole('ROLE_SPITTER') and #spittle.text.length() <= 140 ) or hasRole('ADMIN')")
    public void addSpittle2(Spittle spittle) {

    }

    @PostAuthorize("returnObject.spitter.username == principal.username")
    public Spittle getSpittleById() {
        return new Spittle();
    }

}
