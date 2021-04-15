package jms.hello;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @author : Alex
 * @created : 13.04.2021, вторник
 **/
public class HelloProducer implements Runnable {
    private final String url;

    public HelloProducer(String url){
        this.url = url;
    }

    @Override
    public void run() {
        try {
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("TEST.FOO");
            System.out.println("destination="+destination);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);


            // Create a messages
            String text = "Hello world! From: " + Thread.currentThread().getName();
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

}
