package com.github.philwinder.prometheus.java.springboot;

import io.prometheus.client.Histogram;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@RestController
public class Application {
    static final Histogram requestLatency = Histogram.build()
            .name("http_request_duration_seconds")
            .help("HTTP request duration (seconds).")
            .register();

    @RequestMapping("/")
    String root() {
        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
            return "Hello Spring Boot World!";
        } finally {
            requestTimer.observeDuration();
        }

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
