package com.yemyatthu.usercentricsappchallenge.domain.repository

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData

interface ServiceDataRepository {
    suspend fun getServicesData(templateIds: List<String>): List<ServiceData>
}