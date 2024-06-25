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
-​⬤
