# Fleet Manager TFM

![Vert.x](https://img.shields.io/badge/vert.x-4.5.8-purple.svg)

Fleet Manager TFM is a reactive fleet management system developed as part of a Master's Thesis. The system uses Java Vert.x, PostgreSQL, and MQTT to handle real-time data from IoT devices, demonstrating the benefits of reactive programming in handling high concurrency and scalability for managing vehicle fleets.

## Features

- **Reactive Backend**: Built with Vert.x for high concurrency and non-blocking operations.
- **Database Integration**: PostgreSQL for efficient data storage and retrieval.
- **Real-Time Data Processing**: Uses MQTT to receive data from IoT devices in real time.
- **Complex Data Structures**: Manages vehicle data including location, status, and alerts.
- **Performance Comparison**: Benchmarks against traditional blocking frameworks like Spring Boot.

## Project Structure

- **application.services**: Contains business logic and data processing services.
  - `DataProcessingService`: Parses and processes incoming data.
  - `MqttSubscriberService`: Subscribes to MQTT topics and handles incoming messages.
- **domain.models**: Defines data models representing fleet data, location, status, and alerts.
- **domain.ports**: Interface definitions for repository pattern implementations.
- **infrastructure.adapters**: Adapters for database (PostgreSQL) and MQTT client integration.
  - `PgFleetDataRepositoryAdapter`: Implements data storage using PostgreSQL.
  - `MqttClientAdapter`: Manages MQTT client connections and subscriptions.
- **infrastructure.configuration**: Configuration classes for Vert.x and related services.
- **util**: Utility classes for JSON processing and other helper functions.
  - `JsonUtil`: Handles JSON serialization and deserialization.

## Building and Running the Project

### Prerequisites

- Java 11 or later
- Maven 3.6.3 or later
- PostgreSQL 12 or later
- MQTT Broker (e.g., Mosquitto)

### Building the Project

To clean and test the project:
```sh
./mvnw clean test
```
To compile and run the application:
```sh
./mvnw clean compile exec:java
```
Configuration

Update the configuration settings in the VertxConfig class as needed, including database connection details and MQTT broker settings.

Usage

	1.	Start the Application:
Ensure PostgreSQL and MQTT broker are running. Then, start the application using the command above.
	2.	Send Data to MQTT Broker:
Use an MQTT client to publish data to the relevant topics:
	•	fleet/{vehicleId}/data
	•	fleet/{vehicleId}/status
	•	fleet/{vehicleId}/alerts
	3.	Monitor Data Processing:
The application will process incoming data, perform necessary calculations, and store the results in PostgreSQL.

**Example JSON Message**

```json
{
  "vehicleId": "1234",
  "timestamp": "2023-06-01T12:34:56Z",
  "location": {
    "latitude": 37.7749,
    "longitude": -122.4194
  },
  "status": {
    "speed": 65.5,
    "fuelLevel": 80.3,
    "engineTemperature": 180.0
  },
  "alerts": [
    {
      "type": "Engine",
      "severity": "High"
    },
    {
      "type": "Fuel",
      "severity": "Low"
    }
  ]
}
```
## Help and Documentation

- [Vert.x Documentation](https://vertx.io/docs/)
- [Vert.x Stack Overflow](https://stackoverflow.com/questions/tagged/vert.x?sort=newest&pageSize=15)
- [Vert.x User Group](https://groups.google.com/forum/?fromgroups#!forum/vertx)
- [Vert.x Discord](https://discord.gg/6ry7aqPWXy)
- [Vert.x Gitter](https://gitter.im/eclipse-vertx/vertx-users)
