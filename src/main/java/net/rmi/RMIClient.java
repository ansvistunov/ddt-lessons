package net.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        int port = 8080;//1099 - default port
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            System.out.println("registry :"+host+":"+port);
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println(stub);
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("RMIClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}