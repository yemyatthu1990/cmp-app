package com.yemyatthu.usercentricsappchallenge.presentation.converter

import com.usercentrics.sdk.UsercentricsUserInteraction
import com.yemyatthu.usercentricsappchallenge.domain.model.UserConsentInteraction
import javax.inject.Inject

class UsercentricsConsentInteractionConverter @Inject constructor(){
    operator fun invoke(action: UsercentricsUserInteraction): UserConsentInteraction {
        return when (action) {
            UsercentricsUserInteraction.ACCEPT_ALL -> UserConsentInteraction.ACCEPT_ALL
            UsercentricsUserInteraction.DENY_ALL -> UserConsentInteraction.DENY_ALL
            UsercentricsUserInteraction.GRANULAR -> UserConsentInteraction.GRANULAR
            UsercentricsUserInteraction.NO_INTERACTION -> UserConsentInteraction.NO_INTERACTION
            else -> UserConsentInteraction.NO_INTERACTION
        }
    }
}