package com.elias.FleetManagerTFM.util;

import com.elias.FleetManagerTFM.domain.models.Alert;
import com.elias.FleetManagerTFM.domain.models.FleetData;
import com.elias.FleetManagerTFM.domain.models.Location;
import com.elias.FleetManagerTFM.domain.models.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static FleetData parseFleetData(String json) throws JsonProcessingException {

    JsonNode rootNode = objectMapper.readTree(json);

    String vehicleId = rootNode.path("vehicleId").asText();
    String timestamp = rootNode.path("timestamp").asText();

    JsonNode locationNode = rootNode.path("location");
    Location location = new Location(
      locationNode.path("latitude").asDouble(),
      locationNode.path("longitude").asDouble()
    );

    JsonNode statusNode = rootNode.path("status");
    Status status = new Status(
      statusNode.path("speed").asDouble(),
      statusNode.path("fuelLevel").asDouble(),
      statusNode.path("engineTemperature").asDouble()
    );

    JsonNode alertsNode = rootNode.path("alerts");
    List<Alert> alerts = new ArrayList<>();
    if (alertsNode.isArray()) {
      for (JsonNode alertNode : alertsNode) {
        Alert alert = new Alert(
          alertNode.path("type").asText(),
          alertNode.path("severity").asText()
        );
        alerts.add(alert);
      }
    }

    FleetData fleetData = new FleetData();
    fleetData.setVehicleId(vehicleId);
    fleetData.setTimestamp(timestamp);
    fleetData.setLocation(location);
    fleetData.setStatus(status);
    fleetData.setAlerts(alerts);

    return fleetData;
  }

  public static String toJson(FleetData fleetData) throws JsonProcessingException {
    return objectMapper.writeValueAsString(fleetData);
  }
}
