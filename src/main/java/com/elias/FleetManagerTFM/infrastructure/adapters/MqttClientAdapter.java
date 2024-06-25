package com.elias.FleetManagerTFM.infrastructure.adapters;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.messages.MqttPublishMessage;

public class MqttClientAdapter {

  private final MqttClient client;

  public MqttClientAdapter(MqttClient client) {
    this.client = client;
  }

  public MqttClient getClient() {
    return client;
  }

  public void connect(String host, int port) {
    client.connect(port, host, ch -> {
      if (ch.succeeded()) {
        System.out.println("Connected to MQTT broker");
        subscribeToTopics();
      } else {
        System.err.println("Failed to connect to MQTT broker: " + ch.cause().getMessage());
      }
    });
  }

  private void subscribeToTopics() {
    String[] topics = {
      "fleet/#",
      "fleet/{vehicleId}/data",
      "fleet/{vehicleId}/status",
      "fleet/{vehicleId}/alerts"
    };

    for (String topic : topics) {
      client.subscribe(topic, 1, ar -> {
        if (ar.succeeded()) {
          System.out.println("Subscribed to topic " + topic);
        } else {
          System.err.println("Failed to subscribe to topic " + topic + ": " + ar.cause().getMessage());
        }
      });
    }

    client.publishHandler(this::handleIncomingMessage);
  }

  public void publish(String topic, String payload, int qos, boolean retained) {
    client.publish(topic,
      Buffer.buffer(payload),
      MqttQoS.valueOf(qos),
      retained,
      false,
      ar -> {
        if (ar.succeeded()) {
          System.out.println("Message published to topic " + topic);
        } else {
          System.err.println("Failed to publish message to topic " + topic + ": " + ar.cause().getMessage());
        }
      });
  }

  private void handleIncomingMessage(MqttPublishMessage message) {
    String topic = message.topicName();
    String payload = message.payload().toString();
    System.out.println("Received message on topic " + topic + ": " + payload);
  }

  public void disconnect() {
    client.disconnect(ar -> {
      if (ar.succeeded()) {
        System.out.println("Disconnected from MQTT broker");
      } else {
        System.err.println("Failed to disconnect from MQTT broker: " + ar.cause().getMessage());
      }
    });
  }
}
