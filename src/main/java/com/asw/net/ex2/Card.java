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

    public final String person;
    public final transient Date createDate;
    public final String cardNumber;
    public double balance;

    @Override
    public String toString() {
        return "Card:cardNumber=" + cardNumber + "\tBalance="
                + balance + "\tPerson=" + person + "\tCreateDate=" + createDate + "";
    }
}

