package com.mvpMatch.vendingmachineservice.model.dtos

class OrderResponseDto {
    /**
     * API should return total they’ve spent, the product they’ve purchased and their change
     * if there’s any (in an array of 5, 10, 20, 50 and 100 cent coins)
     */
    var totalCost = 0
    var product = ""
    var change = emptyList<Int>()
}