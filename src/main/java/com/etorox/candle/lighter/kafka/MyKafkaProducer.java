package com.etorox.candle.lighter.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MyKafkaProducer {
    private final KafkaProducer<String, String> producer;
    public MyKafkaProducer(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "MySpecialProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                Serdes.String().serializer().getClass().getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
        this.producer.send(new ProducerRecord<>("aaa", "bassel"));
    }

    public void startConsuming(){

    }
}
