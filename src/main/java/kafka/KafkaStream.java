package kafka;
import kafka.ex1.CardOperation;
import kafka.ex1.CardOperationSerde;
import kafka.ex1.CardOperationSerializer;
import kafka.ex1.CardOpertaionDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class KafkaStream {
    public static final String INPUT_TOPIC = "billing-operation-in";
    public static final String OUTPUT_TOPIC = "billing-out";
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
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
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


        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, CardOperation> stream =
                builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.serdeFrom(new CardOperationSerializer(), new CardOpertaionDeserializer())));

        /*KTable<String, String> balance = stream
                .map((k,v) -> new KeyValue<>(v.getCard(),v.getOperation()))
                .groupByKey(Grouped.with(Serdes.String(),Serdes.Double()))
                .reduce(Double::sum)
                //.count()
                .mapValues((v) -> v.toString());*/

        KGroupedStream<String, Double> balanceGropedStream = stream
                .map((k,v) -> new KeyValue<>(v.getCard(),v.getOperation()))
                .groupByKey(Grouped.with(Serdes.String(),Serdes.Double()));
        balanceGropedStream.reduce(Double::sum,Materialized.as("BillingValueStore"));


        //balance.toStream().to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));
        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {

            Timer timer = new Timer();

            timer.schedule( new TimerTask() {
                public void run() {
                    printAll(streams);
                }
            }, 5*1000, 5*1000);

            streams.setStateListener((state, state1) -> {
                System.out.println("state="+state+" state1="+state1);
                if (streams.state() == KafkaStreams.State.RUNNING){
                    printAll(streams);
                    //System.out.println("count for hello:" + keyValueStore.get("1"));
                }

            });
            streams.start();
            latch.await();
        } catch (final Throwable e) {
            System.exit(1);
        }
        System.exit(0);

    }



    public static void printAll(KafkaStreams streams){
        System.out.println("print all records...");
        ReadOnlyKeyValueStore<String, Double> keyValueStore =
                streams.store("BillingValueStore", QueryableStoreTypes.keyValueStore());
        KeyValueIterator<String,Double> it = keyValueStore.all();
        while(it.hasNext()){
            KeyValue<String,Double> next = it.next();
            System.out.println("balance " + next.key + ": " + next.value);
        }
    }

}
