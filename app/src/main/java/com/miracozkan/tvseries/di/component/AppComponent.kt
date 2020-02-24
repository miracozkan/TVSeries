package com.miracozkan.tvseries.di.component

import android.app.Application
import com.miracozkan.tvseries.MyApplication
import com.miracozkan.tvseries.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 15:58          │
//└─────────────────────────────┘

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {

    override fun inject(instance: MyApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

}