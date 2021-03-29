/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex2;

/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.rmi.*;
import java.util.Date;

public class BillingClient2 {

	public static void main(String[] args) throws Exception{
		String objectName = "rmi://"+args[0]+"/BillingService";
		System.out.println("Starting...\n");
		BillingService bs = (BillingService)Naming.lookup(objectName);
		System.out.println("done");
		Card c;
		c = bs.getCard("1");
		if (c==null) {
			c = new Card("Piter",new Date(),"1",0.0);
			bs.addNewCard(c);	
		}
		
		
		c = bs.getCard("2");
		if (c==null) {
			c = new Card("Stefan",new Date(),"2",0.0);
			bs.addNewCard(c);
		}
			
		c = bs.getCard("3");
		if (c==null) {
			c = new Card("Nataly",new Date(),"3",0.0);
			bs.addNewCard(c);
		}
		
		System.err.println("begin...\n");
		//String person, Date createDate, String cardNumber,double balance
		int cnt = 30000;
		CardOperation[] co = new CardOperation[cnt];
		for (int i = 0; i < cnt; i++) {
			switch (i%3){
			case 0:	co[i] = new CardOperation("1",1,new Date());break;
			case 1:	co[i] = new CardOperation("2",1,new Date());break;
			case 2:	co[i] = new CardOperation("3",1,new Date());break;
			}
			//String card,double amount,Date operationDate
		}
		bs.processOperations(co);
		///
		System.out.println(bs.getCard("1"));
		System.out.println(bs.getCard("2"));
		System.out.println(bs.getCard("3"));
			
	}
}
