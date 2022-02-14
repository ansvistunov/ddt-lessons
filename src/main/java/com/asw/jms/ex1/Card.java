package com.asw.jms.ex1;
/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.Serializable;
import java.util.*;

public class Card implements Serializable{
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
