package io.github.chandilsachin.prapisample.dagger.components

import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.chandilsachin.prapisample.dagger.modules.ApiModule
import io.github.chandilsachin.prapisample.dagger.modules.ContextModule
import io.github.chandilsachin.prapisample.dagger.modules.RepositoryModule
import io.github.chandilsachin.prapisample.dagger.modules.ViewModelModule
import io.github.chandilsachin.prapisample.modules.pr.PRActivity

//@Component(modules = [AndroidInjectionModule::class, ContextModule::class])
interface ApiComponents {
    //fun inject(prActivity: PRActivity)
}