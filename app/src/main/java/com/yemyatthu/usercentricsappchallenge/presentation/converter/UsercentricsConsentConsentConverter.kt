package com.yemyatthu.usercentricsappchallenge.presentation.converter

import com.usercentrics.sdk.UsercentricsServiceConsent
import com.yemyatthu.usercentricsappchallenge.domain.model.Consent
import javax.inject.Inject

class UsercentricsConsentConsentConverter @Inject constructor() {

    operator fun invoke(userCentricsServiceConsents: List<UsercentricsServiceConsent>) : List<Consent> {
        return userCentricsServiceConsents.map { it.toDomainConsent() }
    }

    private fun UsercentricsServiceConsent.toDomainConsent(): Consent {
        return Consent(
            consentGranted = status,
            dataProcessor = dataProcessor,
            templateId = templateId
        )
    }
}




