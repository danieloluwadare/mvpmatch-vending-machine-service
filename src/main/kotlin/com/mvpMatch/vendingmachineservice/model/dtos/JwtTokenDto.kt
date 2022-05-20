package com.mvpMatch.vendingmachineservice.model.dtos

data class JwtTokenDto(val access_token:String, val token_type:String, val expires_in:String)
