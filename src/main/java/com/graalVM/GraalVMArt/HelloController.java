package com.graalVM.GraalVMArt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    HelloController(){
        new Throwable("HelloController").printStackTrace();
    }

    @GetMapping("/helloJava")
    public String hello() {
        new Throwable("helloJava").printStackTrace();
        return "Hello, GraalVM!";
    }
}
