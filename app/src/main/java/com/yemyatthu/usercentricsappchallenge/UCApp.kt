package com.yemyatthu.usercentricsappchallenge

import android.app.Application
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.models.common.UsercentricsLoggerLevel
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.log

@HiltAndroidApp
class UCApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val options = UsercentricsOptions(
            settingsId = BuildConfig.USERCENTRICS_SETTINGS_ID,
            loggerLevel = if (BuildConfig.DEBUG) UsercentricsLoggerLevel.DEBUG else UsercentricsLoggerLevel.NONE
        )
        Usercentrics.initialize(this, options)
    }
}