package com.asw.jms.ex1;

import javax.jms.*;

public class ObjectListener implements MessageListener {
    BillingService bs;
    private static int count = 0;

    public ObjectListener(BillingService bs) {
        this.bs = bs;
    }

    public void onMessage(Message message) {
        count++;
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage) message;
                Object o = msg.getObject();
                System.out.println(String.format("Reading message %d", count));
                if (o instanceof Card) bs.addNewCard((Card) o);
                else if (o instanceof CardOperation) bs.performCardOperation((CardOperation) o);
            } else {
                System.err.println("Message is not a ObjectMessage");
                bs.printCards();
            }
        } catch (JMSException e) {
            System.err.println("JMSException in onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.err.println("Exception in onMessage():" + t.getMessage());
        }
    }
}
