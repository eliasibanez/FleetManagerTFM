package com.elias.FleetManagerTFM.domain.models;

public class Alert {
  private String type;
  private String severity;

  public Alert(String type, String severity) {
    this.type = type;
    this.severity = severity;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }
}
