package mayton.probe.spring.probespringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProbeSpringsecurityApplication {

	// To add Maven dependencies to the project, please see the Spring Security with Maven article.
	// Both standard spring-security-web and spring-security-config will be required.
	public static void main(String[] args) {

		SpringApplication.run(ProbeSpringsecurityApplication.class, args);

	}

}
