package com.github.philwinder.prometheus.java.springboot;

import io.prometheus.client.Histogram;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

// Standard Spring boot annotation
@SpringBootApplication
// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Must have permission to do this.
@EnableSpringBootMetricsCollector
// For route annotations below.
@RestController
// Main application class. Keep it in one file for simplicity.
public class Application {
    // A Histogram Prometheus Metric
    static final Histogram requestLatency = Histogram.build()
            .name("http_request_duration_seconds")
            .help("HTTP request duration (seconds).")
            .register();    // Register must be called to add it to the output

    // Standard MVC style route mapping
    @RequestMapping("/")
    // Note that we could have used the Spring AOP annotation @PrometheusTimeMethod too.
    String root() {
        // Start the histogram timer
        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
            return "Hello Spring Boot World!";
        } finally {
            // Stop the histogram timer.
            requestTimer.observeDuration();
        }
    }

    // Standard Spring boot main.
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
