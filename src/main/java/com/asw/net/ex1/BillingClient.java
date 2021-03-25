package com.asw.net.ex1;

import java.net.*;
import java.io.*;

public class BillingClient {
    int serverPort = 7896;
    String serverName;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    public BillingClient(String serverName) {
        this.serverName = serverName;
    }

    public static void main(String[] args) {
        BillingClient bc = new BillingClient(/*args[0]*/"localhost");
        try {
            bc.startTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startTest() throws IOException {
        connectToServer();
        sendNewCardOperation("Piter", "1");
        sendNewCardOperation("Stefan", "2");
        sendNewCardOperation("Nataly", "3");
        for (int i = 0; i < 1000; i++) {
            sendAddMoneyOperation("1", i % 10);
            sendAddMoneyOperation("2", i % 20);
            sendAddMoneyOperation("3", i % 30);
        }
        System.out.println("1:" + sendGetCardBalanceOperation("1"));
        System.out.println("2:" + sendGetCardBalanceOperation("2"));
        System.out.println("3:" + sendGetCardBalanceOperation("3"));
        closeConnection();
    }

    void connectToServer() throws UnknownHostException, IOException {
        s = new Socket(serverName, serverPort);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
    }

    void sendNewCardOperation(String personName, String card) throws IOException {
        dos.writeInt(BillingService.ADD_NEW_CARD);
        dos.writeUTF(personName);
        dos.writeUTF(card);
    }

    void sendAddMoneyOperation(String card, double money) throws IOException {
        dos.writeInt(BillingService.ADD_MONEY);
        dos.writeUTF(card);
        dos.writeDouble(money);
    }

    void sendSubMoneyOperation(String card, double money) throws IOException {
        dos.writeInt(BillingService.SUB_MONEY);
        dos.writeUTF(card);
        dos.writeDouble(money);
    }

    double sendGetCardBalanceOperation(String card) throws IOException {
        dos.writeInt(BillingService.GET_CARD_BALANCE);
        dos.writeUTF(card);
        return dis.readDouble();
    }

    void closeConnection() throws IOException {
        dos.writeInt(BillingService.EXIT_CLIENT);
    }

}

