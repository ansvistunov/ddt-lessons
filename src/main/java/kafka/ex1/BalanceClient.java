package kafka.ex1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class BalanceClient {
    final static int cardCount = 3;
    public static void main(String[] args) throws Exception {
        String username = "admin";
        String password = "admin-secret";
        String brokers = "localhost:9092";
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
        properties.put("key.serializer", serializer);
        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config", jaasCfg);
        properties.put("value.deserializer", deserializer);
        properties.put("value.serializer", serializer);

        KafkaProducer<String, String> balanceProducer = new KafkaProducer<>(properties);
        Timer balanceOperation = new Timer();
        balanceOperation.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int card = 0; card < cardCount; card++)
                    balanceProducer.send(new ProducerRecord<>(BillingServer.BALANCE_QUERY_TOPIC, String.valueOf(card)),
                            ((recordMetadata, e) -> {}));
            }
        }, 100, 1000);


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(BillingServer.BALANCE_RESPONSE_TOPIC));
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            if (!records.isEmpty()){
                String out = "\r";
                for(ConsumerRecord<String, String> record: records) {
                    out = out + String.format("%s for %s", record.value(), record.key()) + "\t";
                    writeProgressBar(out);
                }
            }
        }


    }

    public static void writeProgressBar(String message) {
        try {
            System.out.write(message.getBytes());
        } catch (Exception ex) {
        }
    }
}
