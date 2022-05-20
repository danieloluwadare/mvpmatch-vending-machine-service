package com.mvpMatch.vendingmachineservice.model.dtos

import javax.validation.constraints.NotBlank

class UserLoginDto {
    @NotBlank(message = "UserName is required")
    var username = ""
    @NotBlank(message = "Password is required")
    var password = ""
}