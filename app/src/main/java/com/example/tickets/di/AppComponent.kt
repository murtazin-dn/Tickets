package com.example.tickets.di

import android.app.Application
import com.example.domain.di.DomainComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class], dependencies = [DomainComponent::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        fun domainComponent(domainComponent: DomainComponent): Builder
        fun build(): AppComponent
    }
}