package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.Consent
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.domain.model.UserConsentInteraction
import com.yemyatthu.usercentricsappchallenge.domain.repository.ServiceDataRepository
import com.yemyatthu.usercentricsappchallenge.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetConsentedServicesUseCase @Inject constructor(@Named("UsercentricsServiceDataRepository")private val serviceDataRepository: ServiceDataRepository,
                                                      @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke( consentInteraction: UserConsentInteraction,consents: List<Consent>) : List<ServiceData> {
        return withContext(coroutineDispatcher) {
            getConsentedServices(consentInteraction,consents)
        }
    }

    private suspend fun getConsentedServices(consentInteraction: UserConsentInteraction,consents: List<Consent>): List<ServiceData> {
        return when (consentInteraction) {
            UserConsentInteraction.ACCEPT_ALL ->
                consents
            else ->
                consents.filter { it.consentGranted }
        }.map { it.templateId }
            .let {
                serviceDataRepository.getServicesData(it)
            }
    }
}