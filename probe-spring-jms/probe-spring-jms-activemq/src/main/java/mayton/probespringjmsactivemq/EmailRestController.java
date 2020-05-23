package mayton.probespringjmsactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

@RestController
public class EmailRestController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Secured("EMAIL_SENDER")
    @RequestMapping(path = "/send/{message}", method = RequestMethod.POST, consumes = {"application/json"})
    public String sendMessage(@PathVariable String message) {
        try {
            jmsTemplate.convertAndSend("springbootQueue", message);
            return "message sent!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in sending message!";
        }
    }

    @RolesAllowed("EMAIL_SENDER2")
    public String sendMessage2(@PathVariable String message) {
        return "";
    }

}
