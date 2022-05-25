package com.mvpMatch.vendingmachineservice.exceptions

class OrderException(message: String, code: String, val userChange: List<Int>) : BaseException(message, code)