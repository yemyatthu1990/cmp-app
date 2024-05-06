package com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository

import com.yemyatthu.usercentricsappchallenge.data.provider.DataTypeCostProvider
import com.yemyatthu.usercentricsappchallenge.domain.repository.DataTypeCostRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsercentricsDataTypeCostRepositoryImpl @Inject constructor(@Named("UsercentricsDataTypeCostProvider")private val dataTypeCostProvider: DataTypeCostProvider):
    DataTypeCostRepository {
    override suspend fun getCost(dataType: String): Int {
        return dataTypeCostProvider.retrieveCost(dataType)
    }
}