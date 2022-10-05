package com.asw.ws.ex1.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;
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
}