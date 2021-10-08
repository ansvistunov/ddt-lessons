/*
 * Created on 09.07.2006
 *
 * 
 */
package com.asw.rmi.ex1;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

/**
 * @author Administrator
 *
 * 
 */
public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {

	/* (non-Javadoc)
	 * @see com.asw.rmi.ex1.BillingService#addNewCard(java.lang.String)
	 */
	public static final int registryPort = 8080;
	//public static final int registryPort = 1099;
	public static final int proxyPort = 443;

	private	HashMap<String, Double> hash;
	public BillingServiceImpl() throws RemoteException{
		//super(proxyPort);
		super();
		hash = new HashMap<>();
	}
	public void addNewCard(String personName, String card) throws RemoteException {
		hash.putIfAbsent(card, 0.0);
	}

	/* (non-Javadoc)
	 * @see com.asw.rmi.ex1.BillingService#addMoney(java.lang.String, double)
	 */
	public void addMoney(String card, double money) throws RemoteException {
		Double d = hash.get(card);
		if (d != null) hash.put(card,d.doubleValue() + money);
		else throw new NotExistsCardOperation();
	}

	/* (non-Javadoc)
	 * @see com.asw.rmi.ex1.BillingService#subMoney(java.lang.String, double)
	 */
	public void subMoney(String card, double money) throws RemoteException {
		Double d = hash.get(card);
		if (d != null) hash.put(card,d.doubleValue() - money);
		else throw new NotExistsCardOperation();
	}
	
	public double getCardBalance(String card) throws RemoteException{
		Double d = hash.get(card);
		if (d != null) return d.doubleValue();
		else throw new NotExistsCardOperation();
	};
	
	public static void main (String[] args) throws Exception {
		System.out.println(String.format("Initializing BillingService...\nregistry port:%d, proxy port:%d", registryPort, proxyPort));
		BillingService service = new BillingServiceImpl();
		String serviceName = "rmi://localhost:"+registryPort+"/BillingService";
		Naming.rebind(serviceName, service);
		System.out.println("done");
	}

}
