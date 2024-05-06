package com.yemyatthu.usercentricsappchallenge.presentation.converter

import com.usercentrics.sdk.UsercentricsServiceConsent
import com.yemyatthu.usercentricsappchallenge.domain.model.Consent
import org.junit.Assert.assertEquals
import org.junit.Test

class UsercentricsConsentConsentConverterTest {

    private val converter = UsercentricsConsentConsentConverter()

    @Test
    fun `should map list of UsercentricsServiceConsents to Consents`() {
        val usercentricsConsent1 = UsercentricsServiceConsent(
            status = true, templateId = "templateId_1",
            dataProcessor = "Service1", version = "1.0",
            history = emptyList(), type = null, isEssential = false)
        val usercentricsConsent2 = UsercentricsServiceConsent(
            status = false, templateId = "templateId_2",
            dataProcessor = "Service2", version = "1.0",
            history = emptyList(), type = null, isEssential = false)
        val inputConsents = listOf(usercentricsConsent1, usercentricsConsent2)

        val expectedConsent1 = Consent(true, "templateId_1", "Service1")
        val expectedConsent2 = Consent(false, "templateId_2", "Service2")
        val expectedOutput = listOf(expectedConsent1, expectedConsent2) 

        val result = converter(inputConsents)

        assertEquals(expectedOutput, result)
    }
}
