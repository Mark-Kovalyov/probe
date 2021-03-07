package mayton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    static Logger logger = Logger.getLogger("App");

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
        MyBean bean = (MyBean) ctx.getBean("myBean");
        logger.info("myBean.name = " + bean.getName());
        logger.info("myBean.age  = " + bean.getAge());
        MyBean bean2 = (MyBean) ctx.getBean("myBean2");

        bean.destroy();
        bean2.destroy();
    }
}
