package net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Alex
 * @created : 19.03.2021, пятница
 **/
public class SimpleTest implements Runnable{
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new SimpleTest(socket)).start();
        }
    }

    final Socket socket;
    public SimpleTest(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while(true){
                System.out.println("-->"+dis.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
