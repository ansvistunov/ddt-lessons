package com.asw.net.ex2;

import java.net.*;
import java.util.HashMap;
import java.io.*;

public class BillingService extends Thread {
    private int serverPort = 7896;
    private ServerSocket ss;
    private HashMap<String, Card> hash;

    public static void main(String[] args) {
        BillingService bs = new BillingService();
        bs.start();
    }

    public BillingService() {
        hash = new HashMap<>();
    }

    public void run() {
        try {
            ss = new ServerSocket(serverPort);
            System.out.println("Server started");
            while (true) {
                System.out.println("new clients waiting...");
                Socket s = ss.accept();
                System.out.println("Client accepted");
                BillingClientService bcs = new BillingClientService(this, s);
                System.out.println("bcs created");
                bcs.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addNewCard(Card card) {
        hash.putIfAbsent(card.cardNumber, card);
    }

    public void addMoney(String card, double money) {
        Card c = hash.get(card);
        if (c == null) {
            System.out.println("Bad Card number\n");
            return;
        }
        ;
        c.balance += money;
        hash.put(card, c);
    }

    public Card getCard(String card) {
        return hash.get(card);
    }

}


