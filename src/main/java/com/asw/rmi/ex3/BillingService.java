/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex3;

import java.rmi.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BillingService extends Remote {
	public void addNewCard(Card card) throws RemoteException;
	public void processOperations(CardOperation[] operations) throws RemoteException;
	public Card getCard(String card) throws RemoteException;
	
}
