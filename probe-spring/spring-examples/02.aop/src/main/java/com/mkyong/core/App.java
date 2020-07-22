package com.mkyong.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkyong.customer.bo.CustomerBo;

public class App {
    public static void main(String[] args) throws Exception {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        // TODO: Check for Exception in thread "main" org.springframework.beans.factory.parsing.BeanDefinitionParsingException:
        // Configuration problem: Unable to locate Spring NamespaceHandler for XML schema namespace
        // [http://www.springframework.org/schema/aop] Offending resource: class path resource [com/mkyong/Spring-Customer.xml]
        ApplicationContext appContext = new ClassPathXmlApplicationContext("com/mkyong/Spring-Customer.xml");

        CustomerBo customer = (CustomerBo) appContext.getBean("customerBo");

        customer.addCustomer();

        customer.addCustomerReturnValue();

        //customer.addCustomerThrowException();

        customer.addCustomerAround("mkyong");

    }
}