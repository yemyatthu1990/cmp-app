package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.domain.repository.DataTypeCostRepository
import com.yemyatthu.usercentricsappchallenge.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CalculateIndividualCostUseCase @Inject constructor(@Named("UsercentricsDataTypeCostRepository")private val dataTypeCostRepository: DataTypeCostRepository,
                                                         @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {


    suspend operator fun invoke(serviceData: ServiceData): Int {
        return withContext(coroutineDispatcher) {
            calculateIndividualCost(serviceData)
        }
    }

    private suspend fun calculateIndividualCost(serviceData: ServiceData): Int {

        return serviceData.dataTypes
            .map { dataType -> dataTypeCostRepository.getCost(dataType) }
            .sumOf { it }

    }
}