package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class GoodCitizen @Inject constructor() : CostRule {
    override val costModifier: Double
        get() = 0.1

    override suspend fun apply(services: List<String>, serviceCost: Int): Int {
        return if (services.size <= 4)
            (serviceCost - serviceCost.times(costModifier)).roundToInt()
         else serviceCost
    }
}
