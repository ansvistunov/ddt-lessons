package kafka.ex1;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class BillingServer {
    public static final String BILLING_OPERATION_TOPIC = "billing-operation-in";
    public static final String BALANCE_QUERY_TOPIC = "billing-query";
    public static final String BALANCE_RESPONSE_TOPIC = "billing-request";
    public static final String KEY_VALUE_STORE_NAME = "BillingStore";
    public static final String broker = "localhost:8080";
    //public static final String broker = "localhost:9092";
    public static void main(String[] args) {
        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();
        String username = "admin";
        String password = "admin-secret";
        String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);
        final Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "billing");
        props.put("group.id", username + "-consumer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, CardOperationSerde.class.getName());
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", CardOpertaionDeserializer.class.getName());
        props.put("key.serializer", serializer);
        props.put("value.serializer", CardOperationSerializer.class.getName());

        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", jaasCfg);

        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        final KafkaStreams streams = createStreams(props);
        final CountDownLatch streamsReadyLatch = new CountDownLatch(1);
        CompletableFuture.runAsync(()->{
            try {
                streamsReadyLatch.await();
                createBalanceConsumer(props,streams);
            }catch(Exception ex){}
        });

        streams.setStateListener((state, state1) -> {
            System.out.println("state="+state+" state1="+state1);
            if (streams.state() == KafkaStreams.State.RUNNING){
                streamsReadyLatch.countDown();
            }
        });
        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("billing-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (final Throwable e) {
            System.exit(1);
        }
        System.exit(0);

    }

    public static KafkaStreams createStreams(Properties props){
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, CardOperation> stream =
                builder.stream(BILLING_OPERATION_TOPIC, Consumed.with(Serdes.String(), Serdes.serdeFrom(new CardOperationSerializer(), new CardOpertaionDeserializer())));
        KGroupedStream<String, Double> balanceGropedStream = stream
                .map((k,v) -> new KeyValue<>(v.getCard(),v.getOperation()))
                .groupByKey(Grouped.with(Serdes.String(),Serdes.Double()));
        balanceGropedStream.reduce(Double::sum,Materialized.as(KEY_VALUE_STORE_NAME));
        return  new KafkaStreams(builder.build(), props);
    }

    public static void createBalanceConsumer(Properties props, KafkaStreams streams){
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(BALANCE_QUERY_TOPIC));
        ReadOnlyKeyValueStore<String, Double> keyValueStore =
                streams.store(KEY_VALUE_STORE_NAME, QueryableStoreTypes.keyValueStore());

        KafkaProducer<String, String> balanceResultProducer = new KafkaProducer<>(props);

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            String out = "\r"+new Date()+" ";
            if (!records.isEmpty()){
                for(ConsumerRecord<String, String> record: records) {
                    Double balance = keyValueStore.get(record.value());
                    balanceResultProducer.send(new ProducerRecord(BALANCE_RESPONSE_TOPIC,record.value(),String.valueOf(balance)),
                            ((recordMetadata, e) -> {if (e!=null) e.printStackTrace();}));
                    out = out + String.format("%s for key:%s", balance,record.value()) + "\t";
                }
            }
            try{System.out.write(out.getBytes());}catch(Exception e){}
        }
    }



    public static void printAll(KafkaStreams streams){
        System.out.println("print all records...");
        ReadOnlyKeyValueStore<String, Double> keyValueStore =
                streams.store(KEY_VALUE_STORE_NAME, QueryableStoreTypes.keyValueStore());
        KeyValueIterator<String,Double> it = keyValueStore.all();
        while(it.hasNext()){
            KeyValue<String,Double> next = it.next();
            System.out.println("balance " + next.key + ": " + next.value);
        }
    }

}
