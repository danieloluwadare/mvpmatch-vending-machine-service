package com.mvpMatch.vendingmachineservice.annotations

import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EnableMvpSecurity(
    val hasAnyAuthority: Array<RoleType> = [RoleType.DEFAULT],
    val hasAnySpecificValidationRules: Array<ValidationRuleType> = [ValidationRuleType.DEFAULT]
)