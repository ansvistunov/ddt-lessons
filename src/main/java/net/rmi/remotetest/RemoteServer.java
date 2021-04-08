package net.rmi.remotetest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    void registry(RemoteServer server, String name) throws RemoteException;
    String sayHello() throws RemoteException;

}
