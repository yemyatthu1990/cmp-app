package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class BankingSnoopy @Inject constructor() : CostRule{
    override val costModifier: Double
        get() = 1.1

    override suspend fun apply(services: List<String>, serviceCost: Int): Int {
        return if (services.contains("Purchase activity") &&
            services.contains("Bank details") &&
            services.contains("Credit and debit card number")
        )
            serviceCost.times(costModifier).roundToInt()
        else serviceCost
    }

}
