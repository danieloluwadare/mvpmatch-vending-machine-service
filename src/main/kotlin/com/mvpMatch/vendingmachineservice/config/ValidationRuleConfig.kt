package com.mvpMatch.vendingmachineservice.config

import com.mvpMatch.vendingmachineservice.authentication.validations.SpecificValidationRule
import com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations.RoleValidationRule
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class ValidationRuleConfig {

    @Bean(name = ["roleValidationRulesMap"])
    fun getRoleValidationRules(validationRules: List<RoleValidationRule>): HashMap<RoleType, RoleValidationRule> {
        val map = HashMap<RoleType,RoleValidationRule>()
        validationRules.forEach { validationRule-> map[validationRule.getType()]=validationRule }
        return map
    }

    @Bean(name = ["specificValidationRulesMap"])
    fun getSpecificValidationRules(validationRules: List<SpecificValidationRule>): HashMap<ValidationRuleType, SpecificValidationRule> {
        val map = HashMap<ValidationRuleType, SpecificValidationRule>()
        validationRules.forEach { validationRule-> map[validationRule.getType()]=validationRule }
        return map
    }
}