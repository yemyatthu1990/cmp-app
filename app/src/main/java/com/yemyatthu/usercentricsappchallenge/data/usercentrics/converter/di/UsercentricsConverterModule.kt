package com.yemyatthu.usercentricsappchallenge.data.usercentrics.converter.di

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import com.yemyatthu.usercentricsappchallenge.data.converter.DTOConverter
import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.converter.UsercentricsServiceServiceDataDTOConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UsercentricsConverterModule {

    @Binds
    @Named("UsercentricsServiceServiceConverter")
    fun provideUsercentricsServiceServiceDataConverter(impl: UsercentricsServiceServiceDataDTOConverter): DTOConverter<UsercentricsService, ServiceDataDTO>

}