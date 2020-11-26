package com.etorox.candle.lighter.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

@Service
public class MyKafkaStreamer {

    public MyKafkaStreamer() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-streamer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                Serdes.String().serializer().getClass().getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> source = builder.stream("aaa");

        System.out.println("before streaaaam: ");
//        source.flatMapValues((Map<String,String> value) -> {
//            return System.out.println("eee" + value.g);
//        });
        source.foreach((key, value) -> System.out.println("vaaalueee" + value));
//        source.flatMapValues(value -> Arrays.asList(value.split("\\W+")));
//        source.flatMapValues(value -> {
//            return ["aaa" + value];
//        });
        System.out.println("after streaaamm");
//        source.flatMapValues(value -> {
//            System.out.println(value.toString());
//            return 1;
//        });

        final Topology topology = builder.build();
//        System.out.println(topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);
        streams.start();
    }

}
