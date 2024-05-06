package com.yemyatthu.usercentricsappchallenge.data

/**
 * Create SDK Helper Class to get third party SDK Instance
 * For easier testing
 */
interface SDKHelper<T> {
    suspend fun getSDKInstance(): T
}