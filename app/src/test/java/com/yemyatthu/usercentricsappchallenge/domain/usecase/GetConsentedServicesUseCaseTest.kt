package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.Consent
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.domain.model.UserConsentInteraction
import com.yemyatthu.usercentricsappchallenge.domain.repository.ServiceDataRepository
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import kotlinx.coroutines.test.runTest

class GetConsentedServicesUseCaseTest {

    private val mockServiceDataRepo: ServiceDataRepository = mock()
    private val useCase = GetConsentedServicesUseCase(mockServiceDataRepo, Dispatchers.Unconfined)

    @Test
    fun `should return all services when user accept all consents`() = runTest {

        val consents = listOf(Consent(consentGranted = true, templateId = "templateId_1", dataProcessor = "Service1"),
            Consent(consentGranted = false, templateId = "templateId_2", dataProcessor = "Service2"))
        val interaction = UserConsentInteraction.ACCEPT_ALL
        val expectedServices = listOf(
            ServiceData("Service1","templateId_1", emptyList()),
            ServiceData("Service2","templateId_2", emptyList())

        )
        whenever(mockServiceDataRepo.getServicesData(listOf("templateId_1", "templateId_2"))).thenReturn(expectedServices)
        val result = useCase(interaction, consents)
        assertEquals(expectedServices, result)
    }

    @Test
    fun `should return only consented services when user do no interaction`() = runTest {
        val consents = listOf(Consent(consentGranted = true, templateId = "templateId_1", dataProcessor = "Service1"),
            Consent(consentGranted = false, templateId = "template_2", dataProcessor = "Service2"))
        val interaction = UserConsentInteraction.NO_INTERACTION
        val expectedServices = listOf(
            ServiceData("Service1","templateId_1", emptyList()),
        )
        whenever(mockServiceDataRepo.getServicesData(listOf("templateId_1"))).thenReturn(expectedServices)

        val result = useCase(interaction, consents)
        assertEquals(expectedServices, result)
    }

    @Test
    fun `should return only consented services when user deny all consents`() = runTest {
        val consents = listOf(Consent(consentGranted = true, templateId = "templateId_1", dataProcessor = "Service1"),
            Consent(consentGranted = false, templateId = "template_2", dataProcessor = "Service2"))
        val interaction = UserConsentInteraction.DENY_ALL
        val expectedServices = listOf(
            ServiceData("Service1","templateId_1", emptyList()),
        )
        whenever(mockServiceDataRepo.getServicesData(listOf("templateId_1"))).thenReturn(expectedServices)

        val result = useCase(interaction, consents)
        assertEquals(expectedServices, result)
    }

    @Test
    fun `should return only consented services when user grant selective consents`() = runTest {
        val consents = listOf(Consent(consentGranted = true, templateId = "templateId_1", dataProcessor = "Service1"),
            Consent(consentGranted = false, templateId = "template_2", dataProcessor = "Service2"))
        val interaction = UserConsentInteraction.GRANULAR
        val expectedServices = listOf(
            ServiceData("Service1","templateId_1", emptyList()),
        )
        whenever(mockServiceDataRepo.getServicesData(listOf("templateId_1"))).thenReturn(expectedServices)

        val result = useCase(interaction, consents)
        assertEquals(expectedServices, result)
    }

}
