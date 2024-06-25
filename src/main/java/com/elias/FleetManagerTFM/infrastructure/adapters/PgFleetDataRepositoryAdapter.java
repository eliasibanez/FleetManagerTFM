package com.elias.FleetManagerTFM.infrastructure.adapters;

import com.elias.FleetManagerTFM.domain.models.FleetData;
import com.elias.FleetManagerTFM.domain.ports.FleetDataRepositoryPort;
import io.reactivex.rxjava3.core.Single;
import io.vertx.rxjava3.pgclient.PgPool;
import io.vertx.rxjava3.sqlclient.Tuple;

public class PgFleetDataRepositoryAdapter implements FleetDataRepositoryPort {

  private final PgPool client;

  public PgFleetDataRepositoryAdapter(PgPool client) {
    this.client = client;
  }

  @Override
  public Single<Void> save(FleetData fleetData) {
    return client
      .preparedQuery("INSERT INTO fleet_data (vehicle_id, timestamp, latitude, longitude, speed, fuel_level, engine_temperature, alerts) VALUES ($1, $2, $3, $4, $5, $6, $7, $8)")
      .execute(Tuple.of(
        fleetData.getVehicleId(),
        fleetData.getTimestamp(),
        fleetData.getLocation().getLatitude(),
        fleetData.getLocation().getLongitude(),
        fleetData.getStatus().getSpeed(),
        fleetData.getStatus().getFuelLevel()
      ))
      .map(rowSet -> null);
  }
}
