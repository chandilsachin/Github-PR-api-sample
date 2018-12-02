package io.github.chandilsachin.prapisample

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.chandilsachin.prapisample.dagger.components.DaggerAppComponent
import javax.inject.Inject

class Application: Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)
            .build()
            .inject(this)
    }
}