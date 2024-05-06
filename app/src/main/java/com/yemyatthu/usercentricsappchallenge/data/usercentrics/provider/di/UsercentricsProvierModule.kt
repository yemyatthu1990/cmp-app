package com.yemyatthu.usercentricsappchallenge.data.usercentrics.provider.di

import com.yemyatthu.usercentricsappchallenge.data.provider.DataTypeCostProvider
import com.yemyatthu.usercentricsappchallenge.data.provider.ServiceDataProvider
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.provider.UsercentricsInMemoryDataTypeCostProviderImpl
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.provider.UsercentricsServiceDataProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UsercentricsProvierModule {

    @Binds
    @Named("UsercentricsDataTypeCostProvider")
    fun provideUsercentricsDataTypeCostProvider(impl: UsercentricsInMemoryDataTypeCostProviderImpl): DataTypeCostProvider

    @Binds
    @Named("UsercentricsServiceDataProvider")
    fun provideUsercentricsServiceDataProvider(impl: UsercentricsServiceDataProviderImpl): ServiceDataProvider

}