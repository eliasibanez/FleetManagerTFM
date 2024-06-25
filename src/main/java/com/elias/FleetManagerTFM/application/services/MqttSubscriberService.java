package com.elias.FleetManagerTFM.application.services;

import com.elias.FleetManagerTFM.domain.ports.FleetDataRepositoryPort;
import io.reactivex.rxjava3.core.Maybe;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.mqtt.MqttClient;

public class MqttSubscriberService extends AbstractVerticle {
  private final MqttClient client;
  private final DataProcessingService dataProcessingService;
  private final FleetDataRepositoryPort fleetDataRepository;

  public MqttSubscriberService(MqttClient client, DataProcessingService dataProcessingService, FleetDataRepositoryPort fleetDataRepository) {
    this.client = client;
    this.dataProcessingService = dataProcessingService;
    this.fleetDataRepository = fleetDataRepository;
  }

  public void subscribe() {
    client.connect(1883, "localhost", c -> {
      if (c.succeeded()) {
        System.out.println("Connected to MQTT broker");
        client.subscribe("fleet/+/data", 0);

        client.publishHandler(message -> {
          String payload = message.payload().toString();
          processAndSaveData(payload).subscribe(
            () -> System.out.println("Data processed and saved successfully"),
            err -> System.err.println("Failed to process data: " + err.getMessage())
          );
        });
      } else {
        System.err.println("Failed to connect to MQTT broker: " + c.cause().getMessage());
      }
    });
  }

  private Maybe<Void> processAndSaveData(String data) {
    return dataProcessingService.parseData(data)
      .flatMap(dataProcessingService::performCalculations)
      .flatMap(fleetDataRepository::save);
  }
}
