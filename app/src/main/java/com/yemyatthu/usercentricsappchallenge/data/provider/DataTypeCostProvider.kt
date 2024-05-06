package com.yemyatthu.usercentricsappchallenge.data.provider

interface DataTypeCostProvider {
    suspend fun retrieveCost(dataType: String): Int
}
