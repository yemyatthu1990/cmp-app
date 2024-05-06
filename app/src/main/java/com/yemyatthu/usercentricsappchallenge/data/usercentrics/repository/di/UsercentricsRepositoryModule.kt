package com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository.di

import com.yemyatthu.usercentricsappchallenge.domain.repository.DataTypeCostRepository
import com.yemyatthu.usercentricsappchallenge.domain.repository.ServiceDataRepository
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository.UsercentricsDataTypeCostRepositoryImpl
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository.UsercentricsServiceDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UsercentricsRepositoryModule {

    @Binds
    @Named("UsercentricsDataTypeCostRepository")
    fun provideUsercentricsDataTypeCostRepository(impl: UsercentricsDataTypeCostRepositoryImpl): DataTypeCostRepository

    @Binds
    @Named("UsercentricsServiceDataRepository")
    fun provideUsercentricsServiceDataRepository(impl: UsercentricsServiceDataRepositoryImpl): ServiceDataRepository

}