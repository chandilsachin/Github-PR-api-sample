package io.github.chandilsachin.prapisample.modules.pr.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.github.chandilsachin.prapisample.modules.pr.PRViewModel
import io.github.chandilsachin.prapisample.modules.pr.PullRequest

class DataSourceFactory(val viewModel: PRViewModel, val owner: String, val repo: String): DataSource.Factory<Int, PullRequest>(){

    val sourceLiveData = MutableLiveData<PRDataSource>()
    override fun create(): DataSource<Int, PullRequest> {
        val source = PRDataSource(viewModel, owner, repo)
        sourceLiveData.postValue(source)
        return source
    }

}