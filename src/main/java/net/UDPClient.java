package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author : Alex
 * @created : 21.03.2021, воскресенье
 **/
public class UDPClient{
    public static void main(String args[]){
        // args give message contents and destination hostname
        try {

            //localhost
            //        127.0.0.1 ipv4

            DatagramSocket aSocket = new DatagramSocket();      // create socket
            byte [] message = "Hello UDP".getBytes();
            InetAddress aHost = InetAddress.getByName("localhost"); // DNS lookup
            int serverPort = 6789;
            DatagramPacket request =
                    new DatagramPacket(message,  "Hello UDP".length(), aHost, serverPort);
            aSocket.send(request);			  //send message
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);			  //wait for reply
            System.out.println("Reply: " + new String(reply.getData()));
            aSocket.close();
        } catch (SocketException e){ System.out.println("Socket: " + e.getMessage());
            // socket creation failed
        } catch (IOException e){ System.out.println("IO: " + e.getMessage());
            // can be caused by send
        }
    }
}

