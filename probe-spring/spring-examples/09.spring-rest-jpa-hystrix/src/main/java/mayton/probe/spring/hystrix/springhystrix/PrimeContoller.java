package mayton.probe.spring.hystrix.springhystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PrimeContoller {

    private Random random = new Random();

    static Logger logger = LoggerFactory.getLogger(PrimeContoller.class);

    @RequestMapping(value = "/factorize/{n}", method = GET)
    @HystrixCommand(fallbackMethod = "fallbackFactorize",
                        commandKey = "factorize",
                          groupKey = "primeThread",
                 commandProperties = {
                  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                                  value = "1000")
    })
    public Factorization factorize(@PathVariable("n") String n) throws InterruptedException {
        logger.info("::1 factorize {}", n);
        return normalResponse();
    }

    @RequestMapping(value = "/rabinmiller/{n}", method = GET)
    @HystrixCommand(fallbackMethod = "fallbackRabinMiller",
                        commandKey = "rabinMiller",
                          groupKey = "primeThread")
    public RabinMillerReport rabinMiller(@PathVariable("n") String n) {
        logger.info(":: rabin miller with {}", n);
        return new RabinMillerReport();
    }

    public Factorization normalResponse() throws InterruptedException {
        logger.info(" ::2 normalResponse");
        Thread.sleep(random.nextInt(3000));
        return new Factorization(Arrays.asList("2","3"));
    }

    public Factorization fallbackFactorize(String n) {
        logger.info(" ::3 fallback");
        return new Factorization();
    }

    public RabinMillerReport fallbackRabinMiller(String n) {
        RabinMillerReport r = new RabinMillerReport();
        r.setStatus("Error. Timeout.");
        return r;
    }

}
