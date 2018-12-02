package io.github.chandilsachin.prapisample.dagger.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.chandilsachin.prapisample.Application
import io.github.chandilsachin.prapisample.dagger.modules.*
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApiModule::class, RepositoryModule::class, ViewModelModule::class,
    ActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: Application)
}