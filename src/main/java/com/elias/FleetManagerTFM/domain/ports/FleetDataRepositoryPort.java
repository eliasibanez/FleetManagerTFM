package com.elias.FleetManagerTFM.domain.ports;

import com.elias.FleetManagerTFM.domain.models.FleetData;
import io.reactivex.rxjava3.core.Single;

public interface FleetDataRepositoryPort {
  Single<Void> save(FleetData fleetData);
}
