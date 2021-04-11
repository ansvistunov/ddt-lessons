package soapdynamic;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/
@WebService
public class HelloServerDynamic implements HelloDynamic{
    public static final int port = 8080;

    @WebMethod
    @Override
    public String sayHello(String name){
        return String.format("Hello, %s!",name);
    }

    public static void main(String[] args) {
        HelloServerDynamic service = new HelloServerDynamic();
        String url = String.format("http://localhost:%d/HelloDynamic", port);
        Endpoint.publish(url,service);
    }
}
