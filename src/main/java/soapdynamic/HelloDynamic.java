package soapdynamic;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/
@WebService
public interface HelloDynamic {
    @WebMethod
    String sayHello(String name);
}
