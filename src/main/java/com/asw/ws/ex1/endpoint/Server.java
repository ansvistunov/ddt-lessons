package com.asw.ws.ex1.endpoint;

import javax.xml.ws.Endpoint;

/**
 * @author : Alex
 * @created : 11.04.2021, воскресенье
 **/
public class Server {
    public static final int port = 8080;
    public static final String url = "http://localhost:%d/BillingService";

    public static void main(String[] args) {
        Billing service = new Billing();
        Endpoint.publish(String.format(url, port), service);
    }
}
