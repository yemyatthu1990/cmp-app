package com.yemyatthu.usercentricsappchallenge.data.usercentrics.provider

import com.yemyatthu.usercentricsappchallenge.data.provider.DataTypeCostProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsercentricsInMemoryDataTypeCostProviderImpl @Inject constructor(): DataTypeCostProvider {

    /** Use In memory hashmap for storing data types. Can be implemented in a way to
     * fetch these from an API or DB
     */
    private val dataTypesToCost = hashMapOf(
        "Configuration of app setting" to 1,
        "IP address" to 2,
        "User behaviour" to 2,
        "User agent" to 3,
        "App crashes" to -2,
        "Browser information" to 3,
        "Credit and debit card number" to 4,
        "First Name" to 6,
        "Geographic location" to 7,
        "Date and time of visit" to 1,
        "Advertising identifier" to 2,
        "Bank details" to 5,
        "Purchase activity" to 6,
        "Internet service provider" to 4,
        "JavaScript support" to -1
    )

    override suspend fun retrieveCost(dataType: String): Int {
        return dataTypesToCost[dataType] ?: 0
    }
}