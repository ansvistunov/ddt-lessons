package net.rmi.loader.client;

import net.rmi.loader.RemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public class Client {
    public static void main(String[] args) {
        String serverName = "FigureServer";
        String host = (args.length < 1) ? null : args[0];
        int port = 8080;//1099 - default port
        System.setSecurityManager(new SecurityManager());

        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            System.out.println("registry :" + host + ":" + port);
            RemoteObject stub = (RemoteObject) registry.lookup(serverName);
            System.out.println("response: " + stub.createFigure("CIRCLE"));
            System.out.println("response: " + stub.createFigure("RECTANGLE"));
        } catch (Exception e) {
            System.err.println("RMIClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

