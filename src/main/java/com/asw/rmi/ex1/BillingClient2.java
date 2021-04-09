/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex1;

/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.rmi.*;

public class BillingClient2 {

	public static void main(String[] args) throws Exception{
		String objectName = "rmi://"+args[0]+":"+BillingServiceImpl.registryPort+"/BillingService";
		System.out.println("Starting client...\n");
		BillingService bs = (BillingService)Naming.lookup(objectName);
		System.out.println("done. proxy="+bs);
		
		for (int i = 0; i < 10000; i++) {
			try {
				bs.addMoney("1", 1);
			} catch (RemoteException e) {
				bs.addNewCard("Piter", "1");
			}
			try {
				bs.addMoney("2", 1);
			} catch (RemoteException e) {
				bs.addNewCard("Stefan", "2");
			}
			try {
				bs.addMoney("3", 1);
			} catch (RemoteException e) {
				bs.addNewCard("Nataly", "3");
			}
			if (i % 100 == 0) System.out.println(i+" operation done");
		}
		
		System.out.println("1:"+bs.getCardBalance("1"));
		System.out.println("2:"+bs.getCardBalance("2"));
		System.out.println("3:"+bs.getCardBalance("3"));
		
	}
}
