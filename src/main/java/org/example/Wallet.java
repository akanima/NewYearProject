package org.example;

public class Wallet {
    private static double balance;
    public Wallet(double balance){
        this.balance=balance;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public double getBalance(){
        return this.balance;
    }
}
