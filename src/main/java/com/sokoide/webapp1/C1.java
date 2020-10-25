package com.sokoide.webapp1;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static java.lang.String.format;

@RestController
@RequestMapping("/c1")
@Scope("request")
public class C1 {
    Random rand = new Random();

    @GetMapping("/foo/{id}")
    public String fooAction(@PathVariable("id") String id){
        String ret = String.format("Hello %s", id);
        return ret;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/random")
    public String random() {
        int r = rand.nextInt(1000)+10;
        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
        }
        String ret = String.format("%s msec slept", r);
        return ret;
    }
}
