package com.graalVM

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class HelloControllerKotlin {
    constructor(    ){
        Throwable("constructor").printStackTrace()
        println("Env:")
        System.getenv().forEach { (t, u) -> println("key:$t, value:|$u|") }
       // System.getenv().forEach { println(it) }// it => Map.Entry
        println("Properties:")
        System.getProperties().forEach {(t, u) -> println("key:$t, value:|$u|")  }
    }
    @GetMapping("/hello")
    fun hello(): String {
        Throwable("hello").printStackTrace()
        return "Hello, GraalVM!" + Date()
    }
}
