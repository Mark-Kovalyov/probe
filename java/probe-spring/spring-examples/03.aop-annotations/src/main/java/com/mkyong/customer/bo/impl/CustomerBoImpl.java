package com.mkyong.customer.bo.impl;

import com.mkyong.customer.bo.CustomerBo;

import java.util.logging.Logger;

public class CustomerBoImpl implements CustomerBo {
    
    static Logger logger = Logger.getLogger("CustomerBoImpl");

    public void addCustomer(){
        logger.info("addCustomer() is running ");
    }

    public String addCustomerReturnValue(){
        logger.info("addCustomerReturnValue() is running ");
        return "abc";
    }

    public void addCustomerThrowException() throws Exception {
        logger.info("addCustomerThrowException() is running ");
        throw new Exception("Generic Error");
    }

    public void addCustomerAround(String name){
        logger.info("addCustomerAround() is running, args : " + name);
    }

}
