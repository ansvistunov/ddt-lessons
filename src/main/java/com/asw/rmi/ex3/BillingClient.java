/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex3;

import java.rmi.*;

/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BillingClient extends Remote {
	public void changeCardBallance(Card[] cards) throws RemoteException;
}
