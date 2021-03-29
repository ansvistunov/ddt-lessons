package com.asw.net.ex2;
import java.util.*;
import java.io.*;


public class CardOperation implements Serializable {
    public CardOperation(String card,double amount,Date operationDate){
        this.card = card;
        this.amount = amount;
        this.operationDate = operationDate;
    }
    public final String card;
    public final double amount;
    public final Date operationDate;
}

