package com.graalVM

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraalVmArtApplication

fun main(args: Array<String>) {
	runApplication<GraalVmArtApplication>(*args)
}
