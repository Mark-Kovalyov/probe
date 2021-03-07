package mayton.probe.spring.hystrix.springhystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;


public class BenchmarkController {

    Random random = new Random();

    @RequestMapping(value = "/test1/{n}", method = RequestMethod.GET, produces = "text/plain")
    public String test1(@PathVariable("n") String n) {
        throw new RuntimeException();
    }

    @RequestMapping(value = "/test2/{n}", method = RequestMethod.GET, produces = "text/plain")
    public String test2(@PathVariable("n") String n) {
        if (random.nextDouble() < 0.8) {
            throw new RuntimeException();
        }
        return "OK";
    }

    @RequestMapping(value = "/test3/{n}", method = RequestMethod.GET, produces = "text/plain")
    public String test3(@PathVariable("n") String n) {
        if (random.nextDouble() < 0.5) {
            throw new RuntimeException();
        }
        return "OK";
    }

    @RequestMapping(value = "/test4/{n}", method = RequestMethod.GET, produces = "text/plain")
    public String test4(@PathVariable("n") String n) {

        return "OK";
    }


}
