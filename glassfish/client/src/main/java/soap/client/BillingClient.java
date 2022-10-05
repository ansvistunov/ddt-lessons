/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.net.URL;
import soap.client.BillingService;
import soap.client.Billing;
import soap.client.Card;
import soap.client.CardOperation;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;
import javax.xml.ws.WebServiceRef;

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
                System.out.println(service.getBillingPort());
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

