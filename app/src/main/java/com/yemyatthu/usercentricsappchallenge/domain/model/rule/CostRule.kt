package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import kotlin.math.roundToInt

interface CostRule{
    val costModifier: Double
    suspend fun apply(services: List<String>, serviceCost: Int): Int
}



