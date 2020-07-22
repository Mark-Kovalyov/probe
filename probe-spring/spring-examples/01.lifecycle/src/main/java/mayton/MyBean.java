package mayton;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.logging.Logger;

public class MyBean implements InitializingBean, DisposableBean {

    static Logger logger = Logger.getLogger("MyBean");

    private String name;

    private int age;

    public MyBean() {
        logger.info("[1] constructor");
    }

    public MyBean(String name, int age) {
        logger.info("[1.1] constructor with arguments");
        this.name = name;
        this.age = age;
    }

    void initMethod() {
        logger.info("[3] initMethod()");
    }

    void destroyMethod() {
        logger.info("[10] destroyMethod()");
    }

    public void afterPropertiesSet() throws Exception {
        logger.info("[2] after properties set");
    }

    public void destroy() throws Exception {
        logger.info("[4] destroy");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
