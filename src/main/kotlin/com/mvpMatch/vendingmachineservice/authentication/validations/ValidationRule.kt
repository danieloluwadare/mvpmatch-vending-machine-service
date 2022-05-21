package com.mvpMatch.vendingmachineservice.authentication.validations

import java.lang.reflect.Method

interface ValidationRule {
    fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        println("PreValidating Request")
    }
}