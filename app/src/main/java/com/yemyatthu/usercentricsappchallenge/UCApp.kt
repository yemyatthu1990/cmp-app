package com.yemyatthu.usercentricsappchallenge

import android.app.Application
import android.util.Log
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.models.common.UsercentricsLoggerLevel
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.log

@HiltAndroidApp
class UCApp : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            val options = UsercentricsOptions(
                settingsId = BuildConfig.USERCENTRICS_SETTINGS_ID,
                loggerLevel = if (BuildConfig.DEBUG) UsercentricsLoggerLevel.DEBUG else UsercentricsLoggerLevel.NONE
            )
            Usercentrics.initialize(this, options)
        } catch (e: Exception) {
            Log.e(UCApp::class.java.simpleName, e.message.toString())
        }
    }
}