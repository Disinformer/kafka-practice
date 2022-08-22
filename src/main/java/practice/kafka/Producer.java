package practice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static practice.kafka.Constants.BOOTSTRAP_SERVERS;
import static practice.kafka.Constants.TOPIC_NAME;


public class Producer {

    public static void main(String ...args) throws ExecutionException, InterruptedException {
        var props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (var producer = new KafkaProducer<String, String>(props)) {
            var metadata = producer.send(new ProducerRecord<>(TOPIC_NAME, "Hello, World!")).get();
            System.out.println(metadata);
        }
    }

}
