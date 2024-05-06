package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class WhyDoYouCare @Inject constructor() : CostRule {
    override val costModifier: Double
        get() = 1.27

    override suspend fun apply(services: List<String>, serviceCost: Int): Int {
        return if (services.contains("Search terms") &&
            services.contains("Geographic location") &&
            services.contains("IP address")
        )
            serviceCost.times(costModifier).roundToInt()
        else serviceCost
    }
}