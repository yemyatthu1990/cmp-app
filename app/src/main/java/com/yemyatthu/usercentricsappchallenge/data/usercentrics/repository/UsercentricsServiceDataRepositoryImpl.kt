package com.yemyatthu.usercentricsappchallenge.data.usercentrics.repository

import com.yemyatthu.usercentricsappchallenge.data.model.ServiceDataDTO
import com.yemyatthu.usercentricsappchallenge.data.model.toDomainService
import com.yemyatthu.usercentricsappchallenge.data.provider.ServiceDataProvider
import com.yemyatthu.usercentricsappchallenge.domain.model.ServiceData
import com.yemyatthu.usercentricsappchallenge.domain.repository.ServiceDataRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsercentricsServiceDataRepositoryImpl @Inject constructor(@Named("UsercentricsServiceDataProvider")
    private val serviceDataProvider: ServiceDataProvider): ServiceDataRepository {

    /**
     * Get services data using templateIds and combine and merges the data types if there are
     * multiple services with the same name. If template Ids are not the same for services with the same name,
     * use the last service's templateId.
     */
    override suspend fun getServicesData(templateIds: List<String>): List<ServiceData> {
        val servicesData =  serviceDataProvider.getServicesData()
        val newServicesData =  servicesData.filter { templateIds.contains(it.id) }.groupBy { it.name } // Group by name
            .mapValues { (name, values) ->
                if (values.size>1) {
                    val latestId = values.last().id
                    val combinedDataTypes =
                        values.flatMap { it.dataTypes }.distinct()
                    ServiceDataDTO(name, latestId, combinedDataTypes).toDomainService()
                } else values[0].toDomainService()

            }
            .values.toList()
        return newServicesData
    }

}