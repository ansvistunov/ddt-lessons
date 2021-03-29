/*
 * Created on 09.07.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asw.rmi.ex3;

import java.rmi.*;
import java.rmi.activation.*;
import java.util.*;
/**
 * @author Alexey Svistunov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BillingServerAdministrator {
	private static void startServer(String policy,String codebase) throws Exception {
		System.setSecurityManager(new SecurityManager());
		/////
		Properties prop = new Properties();
		prop.put("java.security.policy",policy);
		ActivationGroupDesc gd = new ActivationGroupDesc(prop,null);
		ActivationGroupID gID = ActivationGroup.getSystem().registerGroup(gd);
		ActivationGroup.createGroup(gID,gd,0);
		ActivationDesc desc = new ActivationDesc("com.asw.rmi.ex3.BillingServerImpl",codebase,null);
		ServerService ss = (ServerService) Activatable.register(desc);
		System.out.println("Ready to BillingServerImpl stub");
		Naming.rebind("BillingServer",ss);
		System.out.println("Bound object to registry");
		System.exit(0);
	}
	private static void terminateServer(String hostname) throws Exception {
		System.out.println("Locating server...");
		ServerService ss = (ServerService)Naming.lookup("rmi://"+hostname+"/BillingServer");
		System.out.println("Stopping server...");
		ss.stopServer();
		System.out.println("Server stopped");
		Naming.unbind("rmi://"+hostname+"/BillingServer");
	
	}
	public static void main(String[] args) throws Exception{
		System.out.println("begin BillingServerAdministrator main");
		if (args.length == 2){
			if (args[0].equals("stop")) terminateServer(args[1]);
		}else if (args.length ==3){
			if (args[0].equals("start")) startServer(args[1],args[2]);
		}
	}
}
