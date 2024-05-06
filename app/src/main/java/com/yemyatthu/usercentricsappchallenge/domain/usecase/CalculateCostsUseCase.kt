package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceCost
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CalculateCostsUseCase @Inject constructor(@IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
                                                private val calculateIndividualCost: CalculateIndividualCostUseCase,
                                                private val applyRuleCost: ApplyRuleCostUseCase,
) {

    suspend operator fun invoke(consentedServices: List<ServiceData>): ServiceCost{
        return withContext(coroutineDispatcher) {
            calculateCosts(consentedServices)
        }
    }

    private suspend fun calculateCosts(consentedServices: List<ServiceData>):ServiceCost {
        var totalCost = 0
        val servicesCost = hashMapOf<String, Int>()
        consentedServices.forEach { serviceData ->
            val serviceCostBeforeRule = calculateIndividualCost(serviceData)
            val serviceCostAfterRule = applyRuleCost(serviceData.dataTypes, serviceCostBeforeRule)
            servicesCost[serviceData.name] = serviceCostAfterRule
            totalCost += serviceCostAfterRule
        }
        return ServiceCost(totalCost = totalCost, serviceCosts = servicesCost)
    }
}