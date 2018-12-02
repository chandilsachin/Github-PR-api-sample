package io.github.chandilsachin.prapisample.modules.pr

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import dagger.android.AndroidInjection
import io.github.chandilsachin.prapisample.Application
import io.github.chandilsachin.prapisample.R
import io.github.chandilsachin.prapisample.dagger.factories.ViewModelFactory
import io.github.chandilsachin.prapisample.databinding.ActivityMainBinding
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PRActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewModel: PRViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initDataBinding()
        setSupportActionBar(toolbar)

        mViewModel.toastMessageLiveData.observe(this, Observer {
            it?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        })
    }

    private fun initDataBinding() {
        val layout = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PRViewModel::class.java)
        layout.viewModel = mViewModel
    }
}
