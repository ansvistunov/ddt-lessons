package kafka.ex1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class BillingClient {
    static int count = 0;
    final static int cardCount = 3;

    public static void main(String[] args) throws Exception {
        String username = "admin";
        String password = "admin-secret";
        String brokers = "localhost:8080";
        //String brokers = "localhost:9092";
        String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);
        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();

        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("group.id", username + "-consumer");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", deserializer);
        properties.put("value.deserializer", CardOpertaionDeserializer.class.getName());
        properties.put("key.serializer", serializer);
        properties.put("value.serializer", CardOperationSerializer.class.getName());
        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config", jaasCfg);

        KafkaProducer<String, CardOperation> cardOperationProducer = new KafkaProducer<>(properties);

        Timer produceOperation = new Timer();
        produceOperation.schedule(new TimerTask() {
            @Override
            public void run() {
                cardOperationProducer.send(new ProducerRecord<>(BillingServer.BILLING_OPERATION_TOPIC, new CardOperation(String.valueOf(count % cardCount), 100.0)),
                        (recordMetadata, e) -> writeProgressBar("\r" + String.format("message send. offset:%s send count:%s", recordMetadata.offset(), count++))
                );
            }
        }, 10, 1000);
    }

    public static void writeProgressBar(String message) {
        try {
            System.out.write(message.getBytes());
        } catch (Exception ex) {
        }
    }
}
