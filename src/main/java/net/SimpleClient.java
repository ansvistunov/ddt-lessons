package net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author : Alex
 * @created : 19.03.2021, пятница
 **/
public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("132.145.228.39",8080);
        System.out.println("Connected");
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("HelloServer!!!");
        System.out.println("sended data");
    }
}
