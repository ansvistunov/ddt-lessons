package com.asw.ws.ex1.endpoint;

import java.util.Date;
import java.util.Objects;

public class Card {
	public Card(){}
	public Card(String person, Date createDate, String cardNumber,double balance){
		this.person = person;
		this.createDate = createDate;
		this.cardNumber = cardNumber;
		this.balance = balance;
	}
	public String person;
	public Date createDate;
	public String cardNumber;
	public double balance;
	public String toString(){
		return "Card: cardNumber="+cardNumber+"\tBalance="+balance+"\tPerson="+person+"\tCreateDate="+createDate+"";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Double.compare(card.balance, balance) == 0 && person.equals(card.person) && createDate.equals(card.createDate) && cardNumber.equals(card.cardNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, createDate, cardNumber, balance);
	}
}