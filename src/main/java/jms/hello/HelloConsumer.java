package jms.hello;

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
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("TEST.FOO");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(10000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
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
