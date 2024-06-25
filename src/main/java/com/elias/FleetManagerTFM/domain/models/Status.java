package com.elias.FleetManagerTFM.domain.models;

public class Status {
  private double speed;
  private double fuelLevel;
  private double engineTemperature;

  public Status(double speed, double fuelLevel, double engineTemperature) {
    this.speed = speed;
    this.fuelLevel = fuelLevel;
    this.engineTemperature = engineTemperature;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getFuelLevel() {
    return fuelLevel;
  }

  public void setFuelLevel(double fuelLevel) {
    this.fuelLevel = fuelLevel;
  }

  public double getEngineTemperature() {
    return engineTemperature;
  }

  public void setEngineTemperature(double engineTemperature) {
    this.engineTemperature = engineTemperature;
  }
}
