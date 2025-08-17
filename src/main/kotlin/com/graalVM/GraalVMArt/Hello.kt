package com.graalVM.GraalVMArt

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date


@RestController
class HelloControllerKotlin {
    constructor(    ){
        Throwable("constructor").printStackTrace()
    }
    @GetMapping("/hello")
    fun hello(): String {
        Throwable("hello").printStackTrace()
        return "Hello, GraalVM!" + Date()
    }
}
