package com.yemyatthu.usercentricsappchallenge.domain.usecase

import com.yemyatthu.usercentricsappchallenge.domain.model.rule.CostRule
import com.yemyatthu.usercentricsappchallenge.infrastructure.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ApplyRuleCostUseCase @Inject constructor(
    @Named("BankingSnoopy") private val bankingSnoopy: CostRule,
    @Named("WhyDoYouCare") private val whyDoYouCare: CostRule,
    @Named("GoodCitizen") private val goodCitizen: CostRule,
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {


    suspend operator fun invoke(dataTypes: List<String>, serviceCost: Int): Int {
        return withContext(coroutineDispatcher) {
            applyRuleCost(dataTypes, serviceCost)
        }
    }

    private suspend fun applyRuleCost(dataTypes: List<String>, serviceCost: Int ): Int {
        var ruleCost = serviceCost
        ruleCost = bankingSnoopy.apply(dataTypes,ruleCost)
        ruleCost = whyDoYouCare.apply(dataTypes, ruleCost)
        ruleCost = goodCitizen.apply(dataTypes, ruleCost)
        return ruleCost
    }
}