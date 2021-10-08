/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.ws.ex1.endpoint;

import java.util.Date;
import java.util.Objects;


/**
 * @author Alexey Svistunov
 *
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CardOperation that = (CardOperation) o;
		return Double.compare(that.amount, amount) == 0 && card.equals(that.card) && operationDate.equals(that.operationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(card, amount, operationDate);
	}
}
