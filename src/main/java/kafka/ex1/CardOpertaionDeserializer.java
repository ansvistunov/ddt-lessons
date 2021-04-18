package kafka.ex1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
public class CardOpertaionDeserializer implements Deserializer<CardOperation> {

    @Override
    public CardOperation deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        CardOperation co = null;
        try {
            co = mapper.readValue(bytes, CardOperation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return co;
    }
}
