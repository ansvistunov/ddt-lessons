package com.asw.jms.ex1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;

public class BillingService implements Runnable {
    public static final String url = "tcp://localhost:61616";
    public static final String queueName = "Billing";
    private final ConcurrentHashMap<String, Card> cards;

    public BillingService() {
        cards = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            //fix Forbidden class com.asw.jms.ex1.CardOperation
            connectionFactory.setTrustAllPackages(true);
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //get Queue
            Queue queue = session.createQueue(queueName);
            //Create consumer
            MessageConsumer consumer = session.createConsumer(queue);
            //Create MessageListener
            MessageListener listener = new ObjectListener(this);
            //Register message listener
            consumer.setMessageListener(listener);
            //Start process connection
            connection.start();
            System.out.println("JMS Billing service started");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void addNewCard(Card c) {
        cards.putIfAbsent(c.cardNumber, c);
    }

    public void performCardOperation(CardOperation co) {
        cards.computeIfPresent(co.card, (key, card) -> {
            card.balance += co.amount;
            return card;
        });
    }

    public void printCards() {
        System.out.println(cards);
    }

    public static void main(String[] args) {
        new Thread(new BillingService()).start();
    }
}
