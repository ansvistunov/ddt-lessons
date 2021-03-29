/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex1;

import java.rmi.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BillingService extends Remote {
	void addNewCard(String personName, String card) throws RemoteException;
	void addMoney(String card, double money) throws RemoteException;
	void subMoney(String card, double money) throws RemoteException;
	double getCardBalance(String card) throws RemoteException;
}
