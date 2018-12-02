package io.github.chandilsachin.prapisample.modules.pr.pagination

import android.arch.paging.PositionalDataSource
import io.github.chandilsachin.prapisample.modules.pr.PRViewModel
import io.github.chandilsachin.prapisample.modules.pr.PullRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PRDataSource(var viewModel: PRViewModel, val owner: String, val repo: String) :
    PositionalDataSource<PullRequest>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PullRequest>) {
        GlobalScope.launch {
            val list = viewModel.fetchOpenPullRequests(owner, repo, params.loadSize)
            withContext(Dispatchers.Main) {
                callback.onResult(list)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PullRequest>) {
        GlobalScope.launch {
            val list = viewModel.fetchInitialOpenPullRequests(owner, repo, params.pageSize)
            withContext(Dispatchers.Main) {
                callback.onResult(list, viewModel.page)
            }
        }
    }


}