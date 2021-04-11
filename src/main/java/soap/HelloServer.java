package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/

@WebService
public class HelloServer{
    public static final int port = 8080;

    @WebMethod
    public String sayHello(String name){
        return String.format("Hello, %s!",name);
    }

    public static void main(String[] args) {
        HelloServer service = new HelloServer();
        String url = String.format("http://localhost:%d/Hello", port);
        Endpoint.publish(url,service);
    }
}
