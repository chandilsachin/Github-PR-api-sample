package io.github.chandilsachin.prapisample.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.chandilsachin.prapisample.dagger.annotations.ViewModelKeyMap
import io.github.chandilsachin.prapisample.dagger.components.ViewModelSubComponent
import io.github.chandilsachin.prapisample.dagger.factories.ViewModelFactory
import io.github.chandilsachin.prapisample.modules.pr.PRViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKeyMap(PRViewModel::class)
    abstract fun getPRViewModel(viewModel: PRViewModel): ViewModel
}