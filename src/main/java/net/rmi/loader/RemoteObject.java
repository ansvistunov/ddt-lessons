package net.rmi.loader;



import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public interface RemoteObject extends Remote {
   Figure createFigure(String figureType) throws RemoteException;
}
