package com.yemyatthu.usercentricsappchallenge.domain.repository

interface DataTypeCostRepository {
    suspend fun getCost(dataType: String): Int
}