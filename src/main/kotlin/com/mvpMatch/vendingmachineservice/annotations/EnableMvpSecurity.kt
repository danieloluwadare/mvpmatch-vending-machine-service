package com.mvpMatch.vendingmachineservice.annotations

import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType

@Target
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EnableMvpSecurity(
    val hasAnyAuthority: Array<RoleType>,
    val hasAnySpecificValidationRules: Array<ValidationRuleType>
)
