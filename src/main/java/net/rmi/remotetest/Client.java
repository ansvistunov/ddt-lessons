package net.rmi.remotetest;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RemoteServer{

    static String serverHost = "";
    static int port = 9090;

    @Override
    public void registry(RemoteServer server, String name) throws RemoteException {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello from client";
    }

    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry(serverHost, port);
        RemoteServer server = (RemoteServer) registry.lookup("Server");
        RemoteServer client = (RemoteServer) UnicastRemoteObject.exportObject(new Client(),0);
        server.registry(client,"Client");
        System.out.println("Client registered...");



    }
}
