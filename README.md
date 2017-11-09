# Prometheus Java Spring Boot

This is a quick example of how to instrument your Java Spring Boot app
with the Prometheus client.


This project is built with:

- Java 8
- Spring Boot 1.5.8
- Prometheus Java SimpleClient 0.1.0

And is packaged as a Docker container.

See the [POM file](./pom.xml) for more details.

## Prometheus

[Prometheus](https://prometheus.io/) is a
[Cloud Native](https://winderresearch.com/what-is-cloud-native/?utm_source=github&utm_medium=web&utm_content=link)
monitoring application.

To instrument our Java code we need to manipulate the metrics each
time a new HTTP request is received.

See [the application](./src/main/java/com/github/philwinder/prometheus/java/springboot/Application.java) for more details.

As an added bonus, Prometheus is able to scrape the Spring Boot
Actuactor metrics for free!

## Building

This project is automatically built by Docker Automated Builds.

To build manually:

`docker build -t python-app .`

## Running

Simply open port 8080 when running as a container:

`docker run -p 8080:8080 --name spring-boot-app philwinder/prometheus-java-spring-boot`

