package com.sokoide.webapp1;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/c1")
@Scope("request")
public class C1 {
    @GetMapping("/foo/{id}")
    public String fooAction(@PathVariable("id") String id){
        String ret = String.format("Hello %s", id);
        return ret;
    }

}
