package com.elias.FleetManagerTFM.infrastructure.configuration;

import com.elias.FleetManagerTFM.infrastructure.adapters.MqttClientAdapter;
import io.vertx.core.Vertx;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;

public class VertxConfig {

  public PgPool configurePgPool(Vertx vertx) {
    PgConnectOptions connectOptions = new PgConnectOptions()
      .setPort(5432)
      .setHost("localhost")
      .setDatabase("fleetdb")
      .setUser("user")
      .setPassword("password");

    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

    return PgPool.pool(vertx, connectOptions, poolOptions);
  }

  public MqttClientAdapter configureMqttClient(Vertx vertx) {
    MqttClientOptions options = new MqttClientOptions()
      .setCleanSession(true)
      .setWillFlag(true)
      .setWillTopic("fleet/disconnect")
      .setWillMessage("Client disconnected")
      .setWillQoS(1)
      .setWillRetain(true)
      .setUsername("mqttuser")
      .setPassword("mqttpassword");

    MqttClient client = MqttClient.create(vertx, options);
    return new MqttClientAdapter(client);
  }
}
