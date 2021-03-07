/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mayton.probe;

public class Account {
    
    private Integer balance;
 
 
    public Account() {}
    
    public Integer getBalance() {
        return balance;
    }
    
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    public Account(Integer balance) {
        super();
        this.balance = balance;
    }
    
    public void withdraw(int money) {
        balance -= money;
    }
}
