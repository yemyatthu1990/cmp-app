package com.yemyatthu.usercentricsappchallenge.data.usercentrics.converter

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import com.yemyatthu.usercentricsappchallenge.data.converter.DTOConverter
import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsercentricsServiceServiceDataDTOConverter @Inject constructor(): DTOConverter<UsercentricsService, ServiceDataDTO> {
    override fun convert(source: UsercentricsService): ServiceDataDTO {
        return ServiceDataDTO(name = source.dataProcessor ?: "", id = source.templateId ?: "",dataTypes = source.dataCollectedList)
    }
}