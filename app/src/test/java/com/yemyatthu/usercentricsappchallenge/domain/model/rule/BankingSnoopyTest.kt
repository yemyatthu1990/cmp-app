package com.yemyatthu.usercentricsappchallenge.domain.model.rule

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BankingSnoopyTest {

    private val bankingSnoopy = BankingSnoopy()

    @Test
    fun `apply should increase cost by 10 percent when a service contains Purchase activity, Bank details and Credit and debit card number`() = runTest{
        val dataTypes = listOf("Purchase activity", "Bank details", "Credit and debit card number")
        val originalServiceCost = 100

        val expectedModifiedCost = 110

        val result = bankingSnoopy.apply(dataTypes, originalServiceCost)
        assertEquals(expectedModifiedCost, result)
    }

    @Test
    fun `apply should not modify cost when a service does not contain any of Purchase activity, Bank details and Credit and debit card number`() = runTest{
        val dataTypes = listOf("Location", "Browsing Information") // Does not fulfill conditions
        val originalServiceCost = 50

        val result = bankingSnoopy.apply(dataTypes, originalServiceCost)
        assertEquals(originalServiceCost, result)
    }

    @Test
    fun `apply should not modify cost when a service only contains Purchase activity and Bank details`() = runTest {

        val dataTypes = listOf("Purchase activity", "Bank details") // Does not fulfill conditions
        val originalServiceCost = 50

        val result = bankingSnoopy.apply(dataTypes, originalServiceCost)
        assertEquals(originalServiceCost, result)

    }

}
