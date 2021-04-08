package net.rmi.remotetest;

import javax.lang.model.type.UnionType;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RemoteServer{
    Registry registry;
    static int port = 9090;
    static String serverName = "Server";

    public Server() throws RemoteException {
        super(port);
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registry(RemoteServer server, String name) {
        try {
            System.out.println("begin registry...");
            registry.rebind(name, server);
            System.out.println("Client registered with name "+name + " "+server);
            System.out.println("try call method...");
            try{
                System.out.println(server.sayHello());
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sayHello() {
        return "Hello from server";
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.registry(server, serverName);
        System.out.println("Server started on port "+port +" "+server);
    }

}
