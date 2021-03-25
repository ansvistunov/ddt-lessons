package ex;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : Alex
 * @created : 05.03.2021, пятница
 **/
public class Ex {

    static void connectToServer(String hostname, int port) throws UnknownHostException, IOException {
        Socket socket = new Socket(hostname,port);
    }

    public static void main(String[] args) {
        int x = 10, y = 0;
        int result;
        try {
            connectToServer(args[0], Integer.parseInt(args[1]));
        }catch(UnknownHostException e){
            System.out.println("Bad host name");
            throw new RuntimeException("Bad parameters. Exit");
        }catch(IOException e){
            System.out.println("I/O error");
        }catch(Exception e){
            System.out.println("Unknown Exception:"+e.getMessage());
        }finally {
            System.out.println("Always print");
        }

    }
}
