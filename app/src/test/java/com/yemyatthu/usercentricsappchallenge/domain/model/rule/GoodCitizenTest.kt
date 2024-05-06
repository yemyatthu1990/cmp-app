package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.roundToInt

class GoodCitizenTest {

    private val goodCitizen = GoodCitizen()

    @Test
    fun `apply should reduce cost by 10 percent a service contains 4 or less data types`() = runTest{
        val dataTypes = listOf("Purchase activity", "Bank details", "Credit and debit card number")
        val originalServiceCost = 100
        val expectedModifiedCost = 90

        val result = goodCitizen.apply(dataTypes, originalServiceCost)
        assertEquals(expectedModifiedCost, result)
    }

    @Test
    fun `apply should not modify cost when a service contains more than 4 data types`() = runTest{
        val dataTypes = listOf("Location", "Browsing Information" , "Bank details", "IP Address", "Purchase activity")

        val originalServiceCost = 50

        val result = goodCitizen.apply(dataTypes, originalServiceCost)
        assertEquals(originalServiceCost, result)
    }

    @Test
    fun `apply should reduce cost by 10 percent when a service contains no data types`() = runTest {

        val dataTypes = emptyList<String>()
        val originalServiceCost = 50
        val expectedModifiedCost = 45

        val result = goodCitizen.apply(dataTypes, originalServiceCost)
        assertEquals(expectedModifiedCost, result)

    }

}
