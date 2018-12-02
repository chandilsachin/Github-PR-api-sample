package io.github.chandilsachin.prapisample.modules.pr

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import io.github.chandilsachin.prapisample.Application
import io.github.chandilsachin.prapisample.R
import io.github.chandilsachin.prapisample.dagger.factories.ViewModelFactory
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import javax.inject.Inject

class PRActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProviders.of(this, viewModelFactory).get(PRViewModel::class.java)
    }
}
