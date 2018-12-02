package io.github.chandilsachin.prapisample.dagger.components

import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import io.github.chandilsachin.prapisample.dagger.annotations.ViewModelKeyMap
import io.github.chandilsachin.prapisample.modules.pr.PRViewModel

@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}