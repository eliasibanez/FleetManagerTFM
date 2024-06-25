package com.elias.FleetManagerTFM;

import com.elias.FleetManagerTFM.application.services.DataProcessingService;
import com.elias.FleetManagerTFM.application.services.MqttSubscriberService;
import com.elias.FleetManagerTFM.domain.ports.FleetDataRepositoryPort;
import com.elias.FleetManagerTFM.infrastructure.adapters.MqttClientAdapter;
import com.elias.FleetManagerTFM.infrastructure.adapters.PgFleetDataRepositoryAdapter;
import com.elias.FleetManagerTFM.infrastructure.configuration.VertxConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.rxjava3.mqtt.MqttClient;
import io.vertx.rxjava3.pgclient.PgPool;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    VertxConfig configuration = new VertxConfig();
    PgPool pgPool = (PgPool) configuration.configurePgPool(vertx);
    FleetDataRepositoryPort fleetDataRepository = new PgFleetDataRepositoryAdapter(pgPool);
    MqttClientAdapter mqttClientAdapter = configuration.configureMqttClient(vertx);
    DataProcessingService dataProcessingService = new DataProcessingService(vertx);
    MqttSubscriberService mqttSubscriberService = new MqttSubscriberService((MqttClient) mqttClientAdapter.getClient(), dataProcessingService, fleetDataRepository);

    mqttClientAdapter.connect("localhost", 1883); // Para este TFM, se asume que el broker MQTT está en localhost
    mqttSubscriberService.subscribe();
  }
}
