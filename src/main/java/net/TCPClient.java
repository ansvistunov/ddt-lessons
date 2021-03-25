package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : Alex
 * @created : 21.03.2021, воскресенье
 **/
public class TCPClient {
    public static void main (String args[]) {
        // arguments supply message and hostname
        Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());
            out.writeUTF("Hello, TCP!!!"); // UTF is a string encoding
            String data = in.readUTF(); // read a line of data from the stream
            System.out.println("Received: "+ data) ;
        } catch (UnknownHostException e) {System.out.println("Socket:"+e.getMessage());
            // host cannot be resolved
        } catch (EOFException e) {System.out.println("EOF:"+e.getMessage());
            // end of stream reached
        } catch (IOException e) {System.out.println("readline:"+e.getMessage());
            // error in reading the stream
        } finally {if(s!=null) try {s.close();} catch (IOException e) 		{System.out.println("close:"+e.getMessage());}}
    }
}
