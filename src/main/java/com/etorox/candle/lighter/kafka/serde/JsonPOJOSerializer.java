
package com.etorox.candle.lighter.kafka.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonPOJOSerializer<T> implements Serializer<T> {
  private final static Logger logger = LoggerFactory.getLogger(JsonPOJOSerializer.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  /** Default constructor needed by Kafka */
  public JsonPOJOSerializer() {}

  @Override
  public void configure(Map<String, ?> props, boolean isKey) {}

  @Override
  public byte[] serialize(String topic, T data) {
    if (data == null) return null;

    try {
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error serializing JSON message", e);
    }
  }

  //this method should only be used for logging
  public String serializeToString(T data){
    if (data == null) return null;

    try {
      return objectMapper.writeValueAsString(data);
    } catch (Exception e) {
      logger.error("Error serializing data to string, will not throw an exception");
      return null;
    }
  }

  @Override
  public void close() {}
}
