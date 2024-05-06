package com.yemyatthu.usercentricsappchallenge.data.usercentrics

import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsSDK
import com.yemyatthu.usercentricsappchallenge.data.SDKHelper
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Singleton
class UsercentricsSDKHelperImpl @Inject constructor(): SDKHelper<UsercentricsSDK> {
    override suspend fun getSDKInstance(): UsercentricsSDK {
        return suspendCancellableCoroutine {
                cont ->
            Usercentrics.isReady(onSuccess = {
                cont.resume(Usercentrics.instance)
            }, onFailure = { cont.resumeWithException(UsercentricsSDKException("SDK not initialized yet"))})
        }
    }
}