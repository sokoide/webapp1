package com.sokoide.webapp1;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microsoft.applicationinsights.TelemetryClient;

import java.util.Random;

import static java.lang.String.format;

@RestController
@RequestMapping("/c1")
@Scope("request")
public class C1 {
    private Random rand = new Random();
    private TelemetryClient telemetry = new TelemetryClient();

    public C1(){
    }

    @GetMapping("/foo/{id}")
    public String fooAction(@PathVariable("id") String id){
        telemetry.trackEvent("foo");
        telemetry.trackPageView("fooPage");
        String ret = String.format("Hello %s", id);
        return ret;
    }

    @GetMapping("/hello")
    public String hello() {
        telemetry.trackEvent("hello");
        telemetry.trackPageView("helloPage");
        return "Hello";
    }

    @GetMapping("/random")
    public String random() {
        telemetry.trackEvent("random");
        telemetry.trackPageView("randomPage");
        int r = rand.nextInt(1000)+10;
        telemetry.trackMetric("randomValue", r);
        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
        }
        String ret = String.format("%s msec slept", r);
        return ret;
    }
}
