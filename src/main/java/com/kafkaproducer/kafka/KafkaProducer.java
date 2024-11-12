package com.kafkaproducer.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value(value = "${kafka.topic.name}")
  private String topicName;

  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
      this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message) {
      kafkaTemplate.send(this.topicName, message);
  }
}
