package com.sokoide.webapp1;

import com.microsoft.applicationinsights.telemetry.EventTelemetry;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microsoft.applicationinsights.TelemetryClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.sokoide.webapp1.Webapp1Application.telemetry;
import static java.lang.String.format;

@RestController
@RequestMapping("/c1")
@Scope("request")
public class C1 {
    private Random rand = new Random();
    private Logger logger = LoggerFactory.getLogger(C1.class);

    public C1() {
    }

    @GetMapping("/foo/{id}")
    public String fooAction(@PathVariable("id") String id) {
        logger.info("/foo called. Logger Class=" + logger.getClass());
        telemetry.trackEvent("foo");
        telemetry.trackPageView("fooPage");
        String ret = String.format("Hello %s", id);
        return ret;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.info("/hello called. Logger Class=" + logger.getClass());
        telemetry.trackEvent("hello");
        telemetry.trackPageView("helloPage");
        return "Hello";
    }

    @GetMapping("/random")
    public String random() {
        // log
        logger.info("/random called. Logger Class=" + logger.getClass());
        // event
        telemetry.trackEvent("random");
        // pageview
        telemetry.trackPageView("randomPage");
        int r = rand.nextInt(1000) + 10;
        // custom metric
        telemetry.trackMetric("randomValue", r);
        // custom trace
        Map<String, String> props = new HashMap<>();
        props.put("name", "random service");
        props.put("sleep-ms", String.valueOf(r));
        telemetry.trackTrace("random properties info",
                SeverityLevel.Information,
                props);
        telemetry.trackTrace("random properties warn",
                SeverityLevel.Warning,
                props);
        telemetry.trackTrace("random properties error",
                SeverityLevel.Error,
                props);

        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
            telemetry.trackException(e);
        }

        String ret = String.format("%s msec slept", r);
        return ret;
    }
}
