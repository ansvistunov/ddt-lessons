package soapdynamic;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/
public class HelloClient {
    public static final int port = 8080;
    public static final String url = "http://localhost:%d/HelloDynamic?wsdl";
    public static void main(String[] args) throws Exception {
        Service service = Service.create(new URL(String.format(url,port)), new QName("http://soapdynamic/", "HelloServerDynamicService"));
        HelloDynamic port = service.getPort(new QName("http://soapdynamic/", "HelloServerDynamicPort"), HelloDynamic.class);
        System.out.println(port.sayHello("Dynamic SOAP"));
    }
}
