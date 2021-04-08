package net.rmi.remotetest;

import javax.lang.model.type.UnionType;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteServer{
    Registry registry;
    static int port = 9090;
    static String serverName = "Server";

    public Server(){
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registry(RemoteServer server, String name) {
        try {
            registry.rebind(name, server);
            System.out.println("Client registered with name "+name);
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
        RemoteServer remoteServer = (RemoteServer) UnicastRemoteObject.exportObject(server,0);
        server.registry(remoteServer, serverName);
        System.out.println("Server started on port "+port +" "+remoteServer);
    }

}
