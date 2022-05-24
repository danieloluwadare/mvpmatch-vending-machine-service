package com.mvpMatch.vendingmachineservice.model.dtos

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class OrderResponseDto {
    /**
     * API should return total they’ve spent, the product they’ve purchased and their change
     * if there’s any (in an array of 5, 10, 20, 50 and 100 cent coins)
     */
    var totalCost = 0
    var product = ""
    var change = emptyList<Int>()
}