package com.example.data.di

import android.app.Application
import android.content.Context
import com.example.data.repository.CacheRepository
import com.example.data.repository.OffersRepository
import com.example.network.di.NetworkComponent
import dagger.BindsInstance
import dagger.Component

@DataScope
@Component(modules = [DataModule::class], dependencies = [NetworkComponent::class])
interface DataComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Context): Builder
        fun networkComponent(networkComponent: NetworkComponent): Builder
        fun build(): DataComponent
    }

    fun provideCacheRepository(): CacheRepository
    fun provideOffersRepository(): OffersRepository
}