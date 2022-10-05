package com.asw.ws.ex1.client;

import javax.xml.ws.WebServiceRef;
import com.asw.ws.ex1.endpoint.generated.BillingService;
import com.asw.ws.ex1.endpoint.generated.Billing;
import com.asw.ws.ex1.endpoint.generated.Card;
import com.asw.ws.ex1.endpoint.generated.CardOperation;
import java.net.URL;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

public class BillingClient {
    @WebServiceRef(wsdlLocation="http://localhost:8080/Billing/BillingService?wsdl")
    static BillingService service;

    public static void main(String[] args) throws Exception{
        try {
            service = new BillingService(new URL("http://localhost:8080/Billing/BillingService?wsdl"));
            BillingClient client = new BillingClient();
            client.startTest();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void startTest() {
        try {
	        Billing port = service.getBillingPort();

		Vector<Card> vc = new Vector<Card>();
		Card c = new Card();
		c.setPerson("Piter");
		XMLGregorianCalendar dat = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar();
		dat.setYear(2006);
		dat.setMonth(11);
		dat.setDay(5);
		c.setCreateDate( dat );
		c.setCardNumber("1");
		c.setBalance(0.0);
		vc.add(c);

		c = new Card();
		c.setPerson("Stefan");
		c.setCreateDate( dat );
		c.setCardNumber("2");
		c.setBalance(0.0);
		vc.add(c);

		c = new Card();
		c.setPerson("Nataly");
		c.setCreateDate( dat );
		c.setCardNumber("3");
		c.setBalance(0.0);
		vc.add(c);


		port.addNewCard(vc);
		

		int cnt = 30000;
		Vector<CardOperation> vco = new Vector<CardOperation>();
		CardOperation co;
		for (int i = 0; i < cnt; i++) {
			co = new CardOperation();
			switch (i%3){
			case 0:	co.setCard("1"); co.setAmount(1); co.setOperationDate(dat);vco.add(co);break;
			case 1:	co.setCard("2"); co.setAmount(2); co.setOperationDate(dat);vco.add(co);break;
			case 2: co.setCard("3"); co.setAmount(3); co.setOperationDate(dat);vco.add(co);break;
			}
		}

		port.processOperation(vco);
		
		printCard(port.getCard("1"));	
		printCard(port.getCard("2"));
		printCard(port.getCard("3"));


		
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void printCard(Card card){
	System.out.println(card.getCardNumber()+"\t"+card.getPerson()+"\t"+card.getCreateDate()+"\t"+card.getBalance());
    }
}
