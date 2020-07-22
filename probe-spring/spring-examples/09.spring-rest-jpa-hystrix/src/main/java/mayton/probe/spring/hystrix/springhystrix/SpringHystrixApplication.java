package mayton.probe.spring.hystrix.springhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class SpringHystrixApplication {

	/**
	 * http://127.0.0.1:8083/hystrix
	 *
	 * http://127.0.0.1:8083/actuator
	 *
	 * http://127.0.0.1:8083/actuator/hystrix.stream
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringHystrixApplication.class, args);
	}

}
