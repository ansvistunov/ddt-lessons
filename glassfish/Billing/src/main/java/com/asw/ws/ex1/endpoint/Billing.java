package com.asw.ws.ex1.endpoint;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Map;

@WebService()
public class Billing {

    private Map hash;

    public Billing() {
        hash = new HashMap();
    }

    @WebMethod()
    public void addNewCard(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            hash.put(cards[i].cardNumber, cards[i]);
        }
    }

    @WebMethod()
    public void addMoney(String card, double money) {
        Card c = (Card) hash.get(card);
        if (c == null) {
            System.out.println("Bad Card number\n");
            return;
        };
        c.balance += money;
        hash.put(card, c);
    }

    @WebMethod()
    public void processOperation(CardOperation[] co) {
        for (int i = 0; i < co.length; i++) {
            Card c = (Card) hash.get(co[i].card);
            if (c == null) {
                System.out.println("Bad Card number\n");
            };
            c.balance += co[i].amount;
            hash.put(co[i].card, c);
        }
    }

    @WebMethod()
    public Card getCard(String card) {
        return (Card) hash.get(card);
    }
}
