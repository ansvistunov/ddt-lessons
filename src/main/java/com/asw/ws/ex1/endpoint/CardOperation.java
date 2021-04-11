/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.ws.ex1.endpoint;

import java.util.Date;


/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CardOperation {
	public CardOperation(){}
	public CardOperation(String card,double amount,Date operationDate){
		this.card = card;
		this.amount = amount;
		this.operationDate = operationDate;
	}
	public String card;
	public double amount;
	public Date operationDate;
}
