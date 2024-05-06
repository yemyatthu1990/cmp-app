package com.yemyatthu.usercentricsappchallenge.data.usercentrics.converter

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class UsercentricsServiceServiceDataDTOConverterTest {

    private val converter = UsercentricsServiceServiceDataDTOConverter()

    @Test
    fun `convert should map UsercentricsService to ServiceDataDTO correctly`() {
        val service = UsercentricsService(
            dataProcessor = "Google",
            templateId = "template_123", 
            dataCollectedList = listOf("IP Address", "Email")
        )
        val expectedDTO = ServiceDataDTO(
            name = "Google",
            id = "template_123", 
            dataTypes = listOf("IP Address", "Email")
        )

        val result = converter.convert(service)

        assertEquals(expectedDTO, result)
    }
}
