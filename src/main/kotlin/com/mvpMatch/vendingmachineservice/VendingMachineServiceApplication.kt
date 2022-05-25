package com.mvpMatch.vendingmachineservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class VendingMachineServiceApplication

fun main(args: Array<String>) {
    runApplication<VendingMachineServiceApplication>(*args)
}
