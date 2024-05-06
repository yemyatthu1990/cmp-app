package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.domain.repository.DataTypeCostRepository
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import kotlinx.coroutines.test.runTest

class CalculateIndividualCostUseCaseTest {

    private val mockDataTypeCostRepo: DataTypeCostRepository = mock()
    private val useCase = CalculateIndividualCostUseCase(mockDataTypeCostRepo, Dispatchers.Unconfined)

    @Test
    fun `calculateIndividualCost should calculate individual service cost from data types`() = runTest {

        val serviceData = ServiceData(name = "TestService", templateId = "templateId_123",dataTypes = listOf("data 1", "data 2"))

        whenever(mockDataTypeCostRepo.getCost("data 1")).thenReturn(5)
        whenever(mockDataTypeCostRepo.getCost("data 2")).thenReturn(10)

        val expectedCost = 15

        val result = useCase(serviceData)
        assertEquals(expectedCost, result)
    }
}
