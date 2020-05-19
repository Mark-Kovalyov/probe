package mayton.probespringjmsactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ProbeSpringJmsActivemqApplication {

	@Autowired
	private JmsTemplate jmsTemplate;

	public ProbeSpringJmsActivemqApplication() {
		Email message = new Email();
		message.setBody("Hello");
		message.setTo("world@com");
		jmsTemplate.convertAndSend("springbootQueue", message.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(ProbeSpringJmsActivemqApplication.class, args);
	}

}
