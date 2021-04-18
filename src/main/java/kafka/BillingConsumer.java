package kafka;

import kafka.ex1.CardOperation;
import kafka.ex1.CardOperationSerializer;
import kafka.ex1.CardOpertaionDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class BillingConsumer {
    public static final String INPUT_TOPIC = "billing-in";
    public static final String OUTPUT_TOPIC = "billing-out";
    public static void main(String[] args) {
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
        properties.put("value.deserializer", CardOpertaionDeserializer.class.getName());
        properties.put("key.serializer", serializer);
        properties.put("value.serializer", CardOperationSerializer.class.getName());
        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config", jaasCfg);

        KafkaConsumer<String, CardOperation> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(INPUT_TOPIC));
        int count = 0;
        while(true) {
            ConsumerRecords<String, CardOperation> records = consumer.poll(Long.MAX_VALUE);
            if (!records.isEmpty()){
                for(ConsumerRecord<String, CardOperation> record: records) {
                    // Display record and count
                    count += 1;
                    System.out.println( String.format("%s: value=%s, key=%s",count, record.value(), record.key()));
                }
                consumer.commitSync();
            }
        }

    }
}
