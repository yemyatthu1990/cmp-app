package com.yemyatthu.usercentricsappchallenge.domain.usecase;

import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceCost
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import kotlinx.coroutines.test.runTest

class CalculateCostsUseCaseTest {

    private val mockCalculateIndividualCost: CalculateIndividualCostUseCase = mock()
    private val mockApplyRuleCost: ApplyRuleCostUseCase = mock()
    private val useCase = CalculateCostsUseCase(Dispatchers.Unconfined, mockCalculateIndividualCost, mockApplyRuleCost)

    @Test
    fun `calculateCosts should combine individual costs calculations and rules costs calculations and return a service cost object`() = runTest {

        val serviceData1 = ServiceData("Service1","template1", emptyList())
        val serviceData2 = ServiceData("Service2", "template2", emptyList())
        val consentedServices = listOf(
            serviceData1, serviceData2
        )

        val individualCost1 = 10
        val individualCost2 = 20

        val costAfterRule1 = 9
        val costAfterRule2 = 22

        whenever(mockCalculateIndividualCost(serviceData1)).thenReturn(individualCost1)
        whenever(mockCalculateIndividualCost(serviceData2)).thenReturn(individualCost2)
        whenever(mockApplyRuleCost(emptyList(), individualCost1)).thenReturn(costAfterRule1)
        whenever(mockApplyRuleCost(emptyList(), individualCost2)).thenReturn(costAfterRule2)

        val expectedTotalCost = costAfterRule1 + costAfterRule2
        val expectedServiceCosts = mapOf("Service1" to costAfterRule1, "Service2" to costAfterRule2 )
        val expectedResult = ServiceCost(expectedTotalCost, expectedServiceCosts)
        val actualResult = useCase(consentedServices)
        assertEquals(expectedResult, actualResult)
    }
}
