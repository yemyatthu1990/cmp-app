package com.yemyatthu.usercentricsappchallenge.presentation.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import com.usercentrics.sdk.UsercentricsUserInteraction
import com.yemyatthu.usercentricsappchallenge.domain.model.UserConsentInteraction

class UsercentricsConsentInteractionConverterTest {

    private val converter = UsercentricsConsentInteractionConverter()

    @Test
    fun `should map UsercentricsUserInteraction to UserConsentInteraction`() {

        val testCases = mapOf(
            UsercentricsUserInteraction.ACCEPT_ALL to UserConsentInteraction.ACCEPT_ALL,
            UsercentricsUserInteraction.DENY_ALL to UserConsentInteraction.DENY_ALL,
            UsercentricsUserInteraction.GRANULAR to UserConsentInteraction.GRANULAR,
            UsercentricsUserInteraction.NO_INTERACTION to UserConsentInteraction.NO_INTERACTION,
        )

        testCases.forEach { (input, expectedOutput) ->
            val result = converter(input)
            assertEquals(expectedOutput, result)
        }
    }
}
