/*
 * Created on 09.07.2006
 *
 * 
 */
package com.asw.rmi.ex3;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;
import java.rmi.activation.*;
import java.net.*;
import java.rmi.registry.*;

/**
 * @author Administrator
 *
 * 
 */
public class BillingServiceImpl extends Activatable 
				implements BillingService,ServerService {

	/* (non-Javadoc)
	 * @see com.asw.rmi.ex1.BillingService#addNewCard(java.lang.String)
	 */
	private	Hashtable hash;
	private Set clients;
	private String serverObjectName;
	public BillingServiceImpl(ActivationID id, MarshalledObject obj) throws RemoteException{
		super(id,0);
		hash = new Hashtable();
		clients = new HashSet();
	}
	public void register(String RMIName) throws RemoteException,IllegalArgumentException, 
											MalformedURLException {
		if (RMIName == null) throw new IllegalArgumentException("Registration name cannot be null");
		serverObjectName = RMIName;
		try{
			System.out.println("Creating registry...");
			Registry reg = LocateRegistry.createRegistry(1099);
			System.out.println("Binding server to registry...");
			reg.rebind(serverObjectName,this);
		}catch(RemoteException e){
			System.out.println("Registry already exists. Binding to existing registry");
			Naming.rebind(serverObjectName,this);
		}
		System.out.println("Server bound to registry");
				
	} 
	
	public void addNewCard(Card card) throws RemoteException {
		
		hash.put(card.cardNumber, card);
	}

	/* (non-Javadoc)
	 * @see com.asw.rmi.ex1.BillingService#addMoney(java.lang.String, double)
	 */
	public void processOperations(CardOperation[] operations) throws RemoteException {
		for (int i=0;i<operations.length;i++){
			Card c = (Card)hash.get(operations[i].card);
			if (c==null) throw new NotExistsCardOperation();
			c.balance+=operations[i].amount;
			hash.put(operations[i].card,c);
		}
	}

	
	
	public Card getCard(String card) throws RemoteException{
		Card c = (Card)hash.get(card);
		return c; 
	};
	
	
	/* (non-Javadoc)
	 * @see com.asw.rmi.ex3.ServerService#registerClient(com.asw.rmi.ex3.BillingClient)
	 */
	public void registerClient(BillingClient client) throws RemoteException {
		synchronized(clients) {
			clients.add(client);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.asw.rmi.ex3.ServerService#unregisterClient(com.asw.rmi.ex3.BillingClient)
	 */
	public void unregisterClient(BillingClient client) throws RemoteException {
		synchronized(clients) {
			clients.remove(client);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.asw.rmi.ex3.ServerService#stopServer()
	 */
	public void stopServer() throws RemoteException {
		System.out.println("Terminating server...");
		
	}

}
