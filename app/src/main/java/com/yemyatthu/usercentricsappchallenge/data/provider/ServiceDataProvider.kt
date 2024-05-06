package com.yemyatthu.usercentricsappchallenge.data.provider

import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO

interface ServiceDataProvider {
    suspend fun getServicesData(): List<ServiceDataDTO>
}