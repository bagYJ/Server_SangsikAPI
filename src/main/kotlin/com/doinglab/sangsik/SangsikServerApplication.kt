package com.doinglab.sangsik

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class SangsikServerApplication

fun main(args: Array<String>) {
    runApplication<SangsikServerApplication>(*args)
}
