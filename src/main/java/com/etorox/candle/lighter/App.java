package com.etorox.candle.lighter;

import com.etorox.candle.lighter.kafka.MyKafkaProducer;
import com.etorox.candle.lighter.kafka.MyKafkaStreamer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        boolean lala = true;
        System.out.println("AAAA" + lala);
//        System.out.println("bbb");
        MyKafkaProducer producer = applicationContext.getBean(MyKafkaProducer.class);
        MyKafkaStreamer streamer = applicationContext.getBean(MyKafkaStreamer.class);
        System.out.println("BBBBB = " + producer.toString());
        System.out.println("BBBBB = " + streamer.toString());
        new MyKafkaStreamer();
    }

//    private static
}