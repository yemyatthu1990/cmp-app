package com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository

import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import com.yemyatthu.usercentricsappchallenge.data.provider.ServiceDataProvider
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*

class UsercentricsServiceDataRepositoryImplTest {

    private val mockDataProvider: ServiceDataProvider = mock()
    private val repository = UsercentricsServiceDataRepositoryImpl(mockDataProvider)

    @Test
    fun `getServicesData should filter, combine, and return list of service data`() = runTest {

        val templateIds = listOf("temp_1", "temp_2")

        val mockServiceData = listOf(
            ServiceDataDTO("Service A", "temp_1", listOf("DATA_1")),
            ServiceDataDTO("Service B", "temp_2", listOf("DATA_2", "DATA_3")),
            ServiceDataDTO("Service A", "temp_1", listOf("DATA_1", "DATA_4"))
        )
        runBlocking { whenever(mockDataProvider.getServicesData()).thenReturn(mockServiceData) }

        val expectedResult = listOf(
            ServiceData("Service A", "temp_1", listOf("DATA_1", "DATA_4")),
            ServiceData("Service B", "temp_2", listOf("DATA_2", "DATA_3"))
        )

        val actualResult = repository.getServicesData(templateIds)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `getServicesData should return empty when empty templateIds`() = runTest {
        val templateIds = listOf<String>()
        whenever(mockDataProvider.getServicesData()).thenReturn(
            listOf(
                ServiceDataDTO(
                    "Service A",
                    "temp_1",
                    listOf("DATA_1", "DATA_2")
                ),
            )
        )

        val expectedResult = listOf<ServiceDataDTO>()

        val actualResult = repository.getServicesData(templateIds)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `getServicesData should return empty when no matching data`() = runTest {
        val templateIds = listOf("temp_xyz")
        whenever(mockDataProvider.getServicesData()).thenReturn(
            listOf(
                ServiceDataDTO(
                    "Service A",
                    "temp_1",
                    listOf("DATA_1", "DATA_2")
                ),
            )
        )

        val expectedResult = listOf<ServiceDataDTO>()

        val actualResult = repository.getServicesData(templateIds)
        assertEquals(expectedResult, actualResult)
    }
}
