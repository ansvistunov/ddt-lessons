package kafka.ex1;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class CardOperationSerde implements Serde<CardOperation> {
    @Override
    public Serializer<CardOperation> serializer() {
        return new CardOperationSerializer();
    }

    @Override
    public Deserializer<CardOperation> deserializer() {
        return new CardOpertaionDeserializer();
    }
}
