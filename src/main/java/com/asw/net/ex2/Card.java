package com.asw.net.ex2;

import java.io.Serializable;
import java.util.*;


public class Card implements Serializable {
    public Card(String person, Date createDate, String cardNumber, double balance) {
        this.person = person;
        this.createDate = createDate;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String person;
    public transient Date createDate;
    public String cardNumber;
    public double balance;

    public String toString() {
        return "Card:cardNumber=" + cardNumber + "\tBalance="
                + balance + "\tPerson=" + person + "\tCreateDate=" + createDate + "";
    }
}

