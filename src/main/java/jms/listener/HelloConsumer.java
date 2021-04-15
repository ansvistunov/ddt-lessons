package jms.listener;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * @author : Alex
 * @created : 13.04.2021, вторник
 **/
public class HelloConsumer implements Runnable, ExceptionListener {

    private final String url;

    public HelloConsumer(String url){
        this.url = url;
    }
    @Override
    public void run() {
        try {

            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();


            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("TEST.FOO");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            HelloListener listener = new HelloListener();
            consumer.setMessageListener(listener);
            connection.start();

        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onException(JMSException e) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}
