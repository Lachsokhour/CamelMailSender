# CamelMailSender

## Overview
CamelMailSender is a Spring Boot-based application that leverages Apache Camel to facilitate email sending capabilities. It integrates Camel components for mail and file handling, enabling efficient processing and dispatching of emails.

## Features
- Built with Spring Boot for easy configuration and deployment
- Utilizes Apache Camel for routing and integration
- Supports sending emails via Camel Mail component
- Handles file operations, allowing attachment or content processing
- Embedded web support for potential API extensions

## Technologies Used
- Java 17
- Spring Boot 3.5.0
- Apache Camel 4.12.0 (including mail and file components)
- Spring Boot Mail Starter

## Getting Started

### Prerequisites
- Java 17 or later installed
- Maven installed for build management
- An SMTP server for sending emails (credentials required)

### Building the project
Clone the repository and run the following command to build the application:

```bash
mvn clean install
```

### Running the application
Execute the application with:

```bash
mvn spring-boot:run
```

### Configuration
Configure mail server properties in `application.properties` or `application.yml` to enable email functionalities. Typical properties include:

- `spring.mail.host`
- `spring.mail.port`
- `spring.mail.username`
- `spring.mail.password`
- `spring.mail.properties.mail.smtp.auth`
- `spring.mail.properties.mail.smtp.starttls.enable`

