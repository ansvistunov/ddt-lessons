package net.rmi.remotetest;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RemoteServer{

    static String serverHost = "132.145.228.39";
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
        Registry localregistry = LocateRegistry.createRegistry(8080);
        RemoteServer server = (RemoteServer) registry.lookup("Server");
        System.out.println("server reference="+server);
        Client localClient= new Client();

        RemoteServer client = (RemoteServer) UnicastRemoteObject.exportObject(localClient,0);
        System.out.println("Client reference="+client);
        localregistry.rebind("Client", localClient);
        System.out.println("registered to local registry");
        System.out.println("server ret="+server.sayHello());

        server.registry(client,"Client");
        System.out.println("Client registered...");



    }
}
