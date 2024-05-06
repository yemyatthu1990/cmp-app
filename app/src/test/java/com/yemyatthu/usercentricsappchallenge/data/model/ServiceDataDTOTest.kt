package com.yemyatthu.usercentricsappchallenge.data.model

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import org.junit.Assert.assertEquals
import org.junit.Test

class ServiceDataDTOTest {

    @Test
    fun `toDomainService should map DTO to domain model`() {

        val serviceDataDTO = ServiceDataDTO("Service1", "template_123", listOf("data_1", "data_2"))

        val expectedServiceData = ServiceData("Service1", "template_123", listOf("data_1", "data_2"))

        val result = serviceDataDTO.toDomainService()

        assertEquals(expectedServiceData, result)
    }
}
