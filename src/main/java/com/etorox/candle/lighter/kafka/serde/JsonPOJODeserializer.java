
package com.etorox.candle.lighter.kafka.serde;

import com.etorox.candle.lighter.logging.LogMarkers;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonPOJODeserializer<T> implements Deserializer<T> {
  private final static Logger logger = LoggerFactory.getLogger(JsonPOJODeserializer.class);
  static final String JSON_POJO_CLASS = "JsonPOJOClass";

  private ObjectMapper objectMapper
          = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true);


  private Class<T> tClass;

  /** Default constructor needed by Kafka */
  public JsonPOJODeserializer() {}

  @SuppressWarnings("unchecked")
  @Override
  public void configure(Map<String, ?> props, boolean isKey) {
    tClass = (Class<T>) props.get(JSON_POJO_CLASS);
  }

  @Override
  public T deserialize(String topic, byte[] bytes) {
    if (bytes == null) return null;

    T data;
    try {
      data = objectMapper.readValue(bytes, tClass);
    } catch (Exception e) {
      logger.error(LogMarkers.categories("deserialization", "topic"), e.getMessage());
      throw new SerializationException(e);
    }

    return data;
  }

  @Override
  public void close() {}
}
