package com.mvpMatch.vendingmachineservice.model.dtos

import javax.validation.constraints.NotBlank

class UserRegistrationDto {
    @NotBlank(message = "UserName is required")
    var username = ""

    @NotBlank(message = "Password is required")
    var password = ""

    @NotBlank(message = "Role is required")
    var role = ""
}