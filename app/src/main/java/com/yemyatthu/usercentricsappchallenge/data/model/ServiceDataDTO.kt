package com.yemyatthu.usercentricsappchallenge.data.model

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData

data class ServiceDataDTO(val name: String, val id: String, val dataTypes: List<String>)

fun ServiceDataDTO.toDomainService(): ServiceData {
    return ServiceData(name = name, templateId = id, dataTypes = dataTypes)
}
