/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex3;

/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.rmi.*;
import java.rmi.server.*;

public class BillingClientImpl extends UnicastRemoteObject implements BillingClient {

	public BillingClientImpl() throws RemoteException{
		super();
	}
	public void changeCardBallance(Card[] cards) throws RemoteException{
		////
	};
	public static void main(String[] args) throws Exception{
					
	}
}
