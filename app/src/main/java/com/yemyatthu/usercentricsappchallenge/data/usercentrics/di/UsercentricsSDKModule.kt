package com.yemyatthu.usercentricsappchallenge.data.usercentrics.di

import com.usercentrics.sdk.UsercentricsSDK
import com.yemyatthu.usercentricsappchallenge.data.SDKHelper
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.UsercentricsSDKHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UsercentricsSDKModule {
    @Binds
    @Named("UsercentricsSDK")
    fun provideUsercentricsSDKHelper(impl: UsercentricsSDKHelperImpl): SDKHelper<UsercentricsSDK>
}