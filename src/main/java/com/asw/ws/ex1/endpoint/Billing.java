package com.asw.ws.ex1.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService()
public class Billing {
	private Map<String,Card> hash;

	public Billing(){
		hash = new HashMap<>();
	}

    @WebMethod()
	public void addNewCard(Card[] cards) {
		for (int i = 0; i < cards.length; i++)
			hash.putIfAbsent(cards[i].cardNumber, cards[i]);
	}

    @WebMethod()
	public void addMoney(String card, double money) {
		Card c = hash.get(card);
		if (c == null) {
			System.out.println("Bad Card number\n");
			return;
		}
		c.balance += money;
	}

    @WebMethod()	
	public void processOperation(CardOperation[] co) {
		for (int i = 0; i < co.length; i++){
			Card c = hash.get(co[i].card);
			if (c == null) {
				System.out.println("Bad Card number\n");
			}
			c.balance += co[i].amount;
		}
	}

    @WebMethod()
	public Card getCard(String card)
	{
		return hash.get(card);
	}
}


