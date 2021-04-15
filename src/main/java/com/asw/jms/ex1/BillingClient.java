package com.asw.jms.ex1;


import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.io.Serializable;
import java.util.Date;


public class BillingClient implements Runnable {
    @Override
    public void run() {
        try {
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BillingService.url);
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(BillingService.queueName);
            MessageProducer producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            producer.send(createMessage(new Card("Piter", new Date(), "1", 0.0), session));
            producer.send(createMessage(new Card("Stefan", new Date(), "2", 0.0), session));
            producer.send(createMessage(new Card("Nataly", new Date(), "3", 0.0), session));

            int cnt = 1000;
            for (int i = 0; i < cnt; i++) {
                producer.send(createMessage(new CardOperation((i % 3 + 1) + "", i%3 + 1, new Date()), session));
                if (i % 100 == 0) System.out.println(String.format("Sending message %d", i));
            }

            producer.send(session.createMessage());
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public ObjectMessage createMessage(Serializable data, Session session) throws JMSException{
        ObjectMessage message = session.createObjectMessage();
        message.setObject(data);
        return message;
    }

    public static void main(String[] args) {
        new Thread(new BillingClient()).start();
    }


}
