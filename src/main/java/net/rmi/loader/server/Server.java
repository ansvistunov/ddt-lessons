package net.rmi.loader.server;

import net.rmi.loader.Circle;
import net.rmi.loader.Figure;
import net.rmi.loader.Rectangle;
import net.rmi.loader.RemoteObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public class Server implements RemoteObject{
    public static void main(String[] args) {
        String serverName = "FigureServer";
        final int port = 8080;
        try {
            Server obj = new Server();
            RemoteObject stub = (RemoteObject) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            //Registry registry = LocateRegistry.getRegistry(); //connect to exist registry
            Registry registry = LocateRegistry.createRegistry(port); //start registry on this host
            registry.bind(serverName, stub);
            System.out.println("RMIServer ready");
        } catch (Exception e) {
            System.err.println("RMIServer exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public Figure createFigure(String figureType) throws RemoteException {
        switch(figureType.toUpperCase()){
            case "CIRCLE": return new Circle(0,0,10);
            case "RECTANGLE": return new Rectangle(0, 0, 20, 40);
            default: throw new RuntimeException("Unknown figure type:"+figureType);
        }
    }
}
