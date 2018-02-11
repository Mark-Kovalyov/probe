package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private JmsTemplate jmsTemplate;

    public MyBean(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "someQueue")
    public void processMessage(String content){

    }


}
