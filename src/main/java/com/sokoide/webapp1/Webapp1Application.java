package com.sokoide.webapp1;

import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Webapp1Application {
	public static TelemetryClient telemetry = new TelemetryClient();
	public static void main(String[] args) {
		SpringApplication.run(Webapp1Application.class, args);
	}

}
