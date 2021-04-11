package com.asw.ws.ex1.client;

import com.asw.ws.ex1.webservice.*;
import javax.xml.datatype.*;
import java.net.URL;
import java.util.*;

public class BillingClient {
    public static final int port = 8080;
    public static final String url = "http://localhost:%d/BillingService";
    static BillingService service;

    public static void main(String[] args) throws Exception {
        service = new BillingService(new URL(String.format(url, port)));
        Billing port = service.getBillingPort();
        ArrayList<Card> vc = new ArrayList<>();

        vc.add(createCard("1", "Piter", getCuttentDate(), 0.0));
        vc.add(createCard("2", "Stefan", getCuttentDate(), 0.0));
        vc.add(createCard("3", "Nataly", getCuttentDate(), 0.0));
        port.addNewCard(vc);

        int cnt = 30000;
        ArrayList<CardOperation> vco = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            switch (i % 3) {
                case 0:
                    vco.add(createCardOperation("1", getCuttentDate(), 1));
                    break;
                case 1:
                    vco.add(createCardOperation("2", getCuttentDate(), 2));
                    break;
                case 2:
                    vco.add(createCardOperation("3", getCuttentDate(), 3));
                    break;
            }
        }
        port.processOperation(vco);

        printCard(port.getCard("1"));
        printCard(port.getCard("2"));
        printCard(port.getCard("3"));
    }

    public static void printCard(Card card) {
        System.out.println(card.getCardNumber() + "\t" + card.getPerson() + "\t"
                + card.getCreateDate() + "\t" + card.getBalance());
    }

    public static XMLGregorianCalendar getCuttentDate() {
        GregorianCalendar GC = new GregorianCalendar();
        GC.setTime(new Date());
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(GC);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    public static Card createCard(String cardNumber, String person, XMLGregorianCalendar createDate, double balance) {
        Card c = new Card();
        c.setPerson(person);
        c.setCreateDate(createDate);
        c.setCardNumber(cardNumber);
        c.setBalance(balance);
        return c;
    }

    public static CardOperation createCardOperation(String cardNumber, XMLGregorianCalendar operationDate, double amount) {
        CardOperation co = new CardOperation();
        co.setCard(cardNumber);
        co.setAmount(amount);
        co.setOperationDate(operationDate);
        return co;
    }
}
