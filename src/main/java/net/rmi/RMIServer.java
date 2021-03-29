package net.rmi;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements Hello {
    public String sayHello() {
        return "Hello, world!";
    }
    public static void main(String args[]) {
        try {
            RMIServer obj = new RMIServer();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry(); //connect to exist registry
            Registry registry = LocateRegistry.createRegistry(8080); //start registry on this host
            registry.bind("Hello", stub);
            System.out.println("RMIServer ready");
        } catch (Exception e) {
            System.err.println("RMIServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}