package com.elias.FleetManagerTFM.application.services;

import com.elias.FleetManagerTFM.domain.models.FleetData;
import com.elias.FleetManagerTFM.domain.ports.FleetDataRepositoryPort;
import com.elias.FleetManagerTFM.util.JsonUtil;
import io.reactivex.rxjava3.core.Maybe;
import io.vertx.rxjava3.core.Vertx;

public class DataProcessingService {
  private final Vertx vertx;

  public DataProcessingService(Vertx vertx) {
    this.vertx = vertx;
  }

  public Maybe<FleetData> parseData(String data) {
    return vertx.rxExecuteBlocking(promise -> {

      try {
        FleetData fleetData = JsonUtil.parseFleetData(data);
        promise.complete(fleetData);
      } catch (Exception e) {
        promise.fail(e);
      }
    }, false);
  }

  public Maybe<FleetData> performCalculations(FleetData fleetData) {
    return vertx.rxExecuteBlocking(promise -> {

      fleetData.getStatus().setSpeed(fleetData.getStatus().getSpeed() * 1.1); // Ejemplo de cálculo
      promise.complete(fleetData);
    }, false);
  }

  public Maybe<FleetData> saveData(FleetData fleetData, FleetDataRepositoryPort repository) {
    return vertx.rxExecuteBlocking(promise -> {
      repository.save(fleetData)
        .subscribe(() -> promise.complete(fleetData), promise::fail);
    }, false);
  }

  public Maybe<FleetData> process(String data, FleetDataRepositoryPort repository) {
    return parseData(data)
      .flatMap(this::performCalculations)
      .flatMap(fleetData -> saveData(fleetData, repository));
  }
}
