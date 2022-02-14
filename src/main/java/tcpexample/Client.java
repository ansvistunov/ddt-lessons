package tcpexample;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : Alex
 * @created : 28.10.2021, четверг
 **/
public class Client {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("localhost", 9090);
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("hello world");
        s.close();

    }


}
