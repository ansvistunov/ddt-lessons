package net.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer2 extends UnicastRemoteObject implements Hello {
    public RMIServer2() throws java.rmi.RemoteException {
        super();
    }
    public String sayHello() {
        return "Hello, world!";
    }
    public static void main(String args[]) {
        try {
            RMIServer2 obj = new RMIServer2();
            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry(); //connect to exist registry
            Registry registry = LocateRegistry.createRegistry(8080); //start registry on this host
            registry.bind("Hello", obj);
            //Naming.rebind("rmi://localhost/Hello",obj);
            System.out.println("RMIServer ready");
        } catch (Exception e) {
            System.err.println("RMIServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}