package com.yemyatthu.usercentricsappchallenge.data.usercentrics.provider

import android.util.Log
import com.usercentrics.sdk.UsercentricsSDK
import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import com.yemyatthu.usercentricsappchallenge.data.SDKHelper
import com.yemyatthu.usercentricsappchallenge.data.converter.DTOConverter
import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import com.yemyatthu.usercentricsappchallenge.data.provider.ServiceDataProvider
import com.yemyatthu.usercentricsappchallenge.data.usercentrics.UsercentricsSDKException
import com.yemyatthu.usercentricsappchallenge.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class UsercentricsServiceDataProviderImpl @Inject constructor(@Named("UsercentricsServiceServiceConverter")private val serviceDataDTOConverter: DTOConverter<UsercentricsService, ServiceDataDTO>, @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher, @Named("UsercentricsSDK") private val sdkHelper: SDKHelper<UsercentricsSDK>):
    ServiceDataProvider {
    /**
     * Get Services Data from Usercentrics SDK and convert them to ServiceDataDTO
     */
    override suspend fun getServicesData(): List<ServiceDataDTO> = withContext(context = coroutineDispatcher) {
        try {
            sdkHelper.getSDKInstance()
                .getCMPData().services
                .map { serviceDataDTOConverter.convert(it) }
        }catch (sdkException: UsercentricsSDKException){
            //If sdk initialization throw error, we will return empty list and just log.
            Log.e(UsercentricsServiceDataProviderImpl::class.java.simpleName, "getServicesData: ${sdkException.message}")
            return@withContext emptyList()
        }
    }
}