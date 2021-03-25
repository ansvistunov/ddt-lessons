package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Alex
 * @created : 21.03.2021, воскресенье
 **/
public class TCPServer {
    public static void main (String args[]) {
        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort); // new server port generated
            while(true) {
                Socket clientSocket = listenSocket.accept(); // listen for new connection
                Connection c = new Connection(clientSocket); // launch new thread
            }
        } catch(IOException e) { System.out.println("Listen socket:"+e.getMessage());
        }
    }
}

