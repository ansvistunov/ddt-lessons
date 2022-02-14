package jms.listener;



/**
 * @author : Alex
 * @created : 13.04.2021, вторник
 **/
public class Main {
    public static final String url = "tcp://localhost:61616";
    public static void main(String[] args) {
        new Thread(new HelloProducer(url)).start();
        new Thread(new HelloConsumer(url)).start();
    }
}
