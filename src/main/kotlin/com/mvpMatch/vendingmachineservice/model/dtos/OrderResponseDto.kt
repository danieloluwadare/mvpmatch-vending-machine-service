package com.mvpMatch.vendingmachineservice.model.dtos

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class OrderResponseDto {
    @NotNull(message = "amount is required")
    var productId = 0
    @NotBlank(message = "amountOfProduct is required")
    var amountOfProduct = ""

}