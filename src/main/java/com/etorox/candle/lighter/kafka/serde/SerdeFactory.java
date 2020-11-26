package com.etorox.candle.lighter.kafka.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class SerdeFactory {

  public static <T> Serde<T> build(Class<T> typeClass) {
    Map<String, Object> props = new HashMap<>();
    props.put(JsonPOJODeserializer.JSON_POJO_CLASS, typeClass);

    final Serializer<T> serializer = new JsonPOJOSerializer<>();
    final Deserializer<T> deserializer = new JsonPOJODeserializer<>();

    serializer.configure(props, false);
    deserializer.configure(props, false);

    return Serdes.serdeFrom(serializer, deserializer);
  }
}
