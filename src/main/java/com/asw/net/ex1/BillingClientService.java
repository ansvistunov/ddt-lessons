package com.asw.net.ex1;

import java.io.*;

public class BillingClientService extends Thread {
    DataInputStream dis;
    DataOutputStream dos;
    BillingService bs;

    public BillingClientService(BillingService bs, DataInputStream dis, DataOutputStream dos) {
        this.bs = bs;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {
        System.out.println("ClientService thread started");
        boolean work = true;
        while (work) {
            int command;
            try {
                command = dis.readInt();
                switch (command) {
                    case BillingService.ADD_NEW_CARD:
                        addNewCard();
                        break;
                    case BillingService.ADD_MONEY:
                        addMoney();
                        break;
                    case BillingService.SUB_MONEY:
                        subMoney();
                        break;
                    case BillingService.GET_CARD_BALANCE:
                        getCardBalance();
                        break;
                    case BillingService.EXIT_CLIENT:
                        work = false;
                        break;
                    default:
                        System.out.println("Bad operation:" + command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void addNewCard() throws IOException {
        String personName = dis.readUTF();
        String card = dis.readUTF();
        bs.addNewCard(personName, card);
    }

    void addMoney() throws IOException {
        String card = dis.readUTF();
        double money = dis.readDouble();
        bs.addMoney(card, money);
    }

    void subMoney() throws IOException {
        String card = dis.readUTF();
        double money = dis.readDouble();
        bs.subMoney(card, money);
    }

    void getCardBalance() throws IOException {
        String card = dis.readUTF();
        double money = bs.getCardBalance(card);
        dos.writeDouble(money);
    }
}


