package soap;

import soap.webservice.HelloServer;
import soap.webservice.HelloServerService;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/
public class HelloClient {
    public static final int port = 8080;
    public static final String url = "http://localhost:%d/Hello?wsdl";

    public static void main(String[] args) throws MalformedURLException {
        HelloServer service = new HelloServerService(new URL(String.format(url, port))).getHelloServerPort();
        System.out.println(service.sayHello("SOAP"));
    }
}
