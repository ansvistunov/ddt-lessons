package tcpexample;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * @author : Alex
 * @created : 28.10.2021, четверг
 **/
public class Server {
    List<Server> servers;
    Map<String, Server> hash;

    public static void main(String[] args){
        try {
            ServerSocket ss = new ServerSocket(9090);
            while(true) {
                Socket socket = ss.accept();
                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String data = dis.readUTF();
                System.out.println(data);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
