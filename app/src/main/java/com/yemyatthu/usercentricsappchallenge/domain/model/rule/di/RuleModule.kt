package com.yemyatthu.usercentricsappchallenge.domain.model.rule.di

import com.yemyatthu.usercentricsappchallenge.domain.model.rule.BankingSnoopy
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.CostRule
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.GoodCitizen
import com.yemyatthu.usercentricsappchallenge.domain.model.rule.WhyDoYouCare
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface RuleModule {

    @Binds
    @Named("BankingSnoopy")
    fun provideBankingSnoopyImpl(impl: BankingSnoopy): CostRule

    @Binds
    @Named("WhyDoYouCare")
    fun provideWhyDoYouCareImpl(impl: WhyDoYouCare): CostRule

    @Binds
    @Named("GoodCitizen")
    fun provideGoodCitizenImpl(impl: GoodCitizen): CostRule
}