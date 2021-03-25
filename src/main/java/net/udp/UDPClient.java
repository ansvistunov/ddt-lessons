package net.udp;

import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {
        // args give message contents and destination hostname
        try {
            int serverPort = 8080;
            String serverHost = args[1];
            String msg = args[0];

            DatagramSocket aSocket = new DatagramSocket();      // create socket
            byte[] message = msg.getBytes();
            InetAddress aHost = InetAddress.getByName(serverHost); // DNS lookup

            DatagramPacket request =
                    new DatagramPacket(message, message.length, aHost, serverPort);
            System.out.println("new packet to "+aHost);
            aSocket.send(request);              //send message
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);              //wait for reply
            System.out.println("Reply: " + new String(reply.getData()));
            aSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage()); // socket creation failed
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage()); // can be caused by send
        }
    }
}
