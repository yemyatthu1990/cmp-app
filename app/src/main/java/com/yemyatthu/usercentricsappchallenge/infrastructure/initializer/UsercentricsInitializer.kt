package com.yemyatthu.usercentricsappchallenge.infrastructure.initializer

import android.content.Context
import androidx.startup.Initializer
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.yemyatthu.usercentricsappchallenge.BuildConfig

class UsercentricsInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        val options = UsercentricsOptions(settingsId = BuildConfig.USERCENTRICS_SETTINGS_ID)
        Usercentrics.initialize(context, options)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}