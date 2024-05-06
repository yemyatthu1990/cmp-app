package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class WhyDoYouCareTest {

    private val whyDoYouCare = WhyDoYouCare()

    @Test
    fun `apply should increase cost by 27 percent when a service contains Search terms, Geographic location and IP address`() = runTest{
        val dataTypes = listOf("Search terms", "Geographic location", "IP address")
        val originalServiceCost = 100

        val expectedModifiedCost = 127

        val result = whyDoYouCare.apply(dataTypes, originalServiceCost)
        assertEquals(expectedModifiedCost, result)
    }

    @Test
    fun `apply should not modify cost when a service does not contain any of Search terms, Geographic location and IP address`() = runTest{
        val dataTypes = listOf("Location", "Browsing Information")
        val originalServiceCost = 50

        val result = whyDoYouCare.apply(dataTypes, originalServiceCost)
        assertEquals(originalServiceCost, result)
    }

    @Test
    fun `apply should not modify cost when a service only contains Search terms and Geographic location `() = runTest {

        val services = listOf("Search terms", "Geographic location")
        val originalServiceCost = 50

        val result = whyDoYouCare.apply(services, originalServiceCost)
        assertEquals(originalServiceCost, result)

    }

}
