package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.rule.BankingSnoopy
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.CostRule
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.GoodCitizen
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.WhyDoYouCare
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import kotlinx.coroutines.test.runTest // Or any appropriate coroutine testing lib
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.kotlin.*
import kotlinx.coroutines.test.runTest
import kotlin.math.roundToInt

@RunWith(Parameterized::class)
class ApplyRuleCostUseCaseTest(
    private val dataTypes: List<String>,
    private val finalExpectedCost: Int,
    private val prameterizedDescription: String
) {

    private val bankingSnoopy : CostRule = BankingSnoopy()
    private val whyDoYouCare : CostRule = WhyDoYouCare()
    private val goodCitizen : CostRule = GoodCitizen()
    private val useCase = ApplyRuleCostUseCase(bankingSnoopy, whyDoYouCare, goodCitizen,Dispatchers.Unconfined)

    @Test
    fun `should apply rules based on data types and return modified cost`() = runTest {
        val initialServiceCost = 100
        val result = useCase(dataTypes, initialServiceCost)
        assertEquals(finalExpectedCost, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "data types contains {0} -> {2}")
        fun testData(): Collection<Array<Any>> {
            return listOf(
                arrayOf(listOf("Purchase activity", "Bank details", "Credit and debit card number", "Search terms", "Geographic location", "IP address"), 140, "should apply Banking snoopy and Why do you care"), // Banking snoopy and Why do you care
                arrayOf(listOf("Purchase activity", "Bank details", "Credit and debit card number", "Search terms"), 99,  "should apply Banking snoopy and Good citizen"), // Banking snoopy and Good citizen
                arrayOf(listOf( "Credit and debit card number", "Search terms", "Geographic location", "IP address"), 114,  "should apply Why do you care and Good citizen"), // Good citizen and Why do you care
                arrayOf(listOf("Purchase activity", "Bank details", "Credit and debit card number", "Number of page views", "Geographic location", "IP address"), 110,  "should apply Banking snoopy"), //Only Banking snoopy
                arrayOf(listOf("Purchase activity", "Bank details", "Number of page views","Search terms", "Geographic location", "IP address"), 127, "should apply Why do you care"), //Only Why do you care
                arrayOf(emptyList<String>(), 90,  "should apply Good citizen"), // Only Good Citizen
                arrayOf(listOf("Browser Info"), 90, " should apply Good citizen"),  // Only Good Citizen

            )
        }
    }
}
