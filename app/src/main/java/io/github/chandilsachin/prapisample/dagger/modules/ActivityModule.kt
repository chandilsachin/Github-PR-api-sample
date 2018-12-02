package io.github.chandilsachin.prapisample.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.chandilsachin.prapisample.modules.pr.PRActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributePrActivity(): PRActivity
}