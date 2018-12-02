package io.github.chandilsachin.prapisample.modules.pr

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View
import io.github.chandilsachin.prapisample.R
import io.github.chandilsachin.prapisample.modules.pr.pagination.DataSourceFactory
import io.github.chandilsachin.prapisample.modules.pr.pagination.PRDataSource
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.concurrent.Executor
import javax.inject.Inject


class PRViewModel @Inject constructor(var remoteRepository: RemoteRepository) : ViewModel() {

    private val job = Job()
    val loading: ObservableBoolean = ObservableBoolean(false)
    val messageVisibility: ObservableInt = ObservableInt(View.VISIBLE)
    val message: ObservableInt = ObservableInt(R.string.search_pr_message)
    val toastMessageLiveData = MutableLiveData<Int>()
    //val openPullRequestLiveData = MutableLiveData<List<PullRequest>>()
    private var openPullRequestLiveData:LiveData<PagedList<PullRequest>> = MutableLiveData<PagedList<PullRequest>>()
    var page = 0;
    var dataSourceFactory: DataSourceFactory? = null
    lateinit var prDataSource: DataSource<Int, PullRequest>

    suspend fun fetchInitialOpenPullRequests(owner: String, repo: String, pageSize: Int): List<PullRequest> {
        page = 0
        withContext(Dispatchers.Main) {
            loading.set(true)
        }
        var list: List<PullRequest>
        try {
            list = remoteRepository.fetchOpenPRs(owner, repo, page, pageSize)
            withContext(Dispatchers.Main) {
                if (list.isNotEmpty()) {
                    //openPullRequestLiveData.value = list
                    messageVisibility.set(View.GONE)
                } else {
                    messageVisibility.set(View.VISIBLE)
                    message.set(R.string.no_pr_message)
                }
                loading.set(false)
            }
        } catch (e: HttpException) {
            list = emptyList()
            withContext(Dispatchers.Main) {
                if (e.code() == 404) {
                    toastMessageLiveData.value = R.string.wrong_repo_path
                } else {
                    toastMessageLiveData.value = R.string.something_went_wrong
                }
                messageVisibility.set(View.VISIBLE)
                message.set(R.string.search_pr_message)
                loading.set(false)
            }
        }
        return list
    }

    suspend fun fetchOpenPullRequests(owner: String, repo: String, pageSize: Int): List<PullRequest> {
        page++
        return remoteRepository.fetchOpenPRs(owner, repo, page, pageSize)
    }

    fun onClickSearchButton(repoPath: String): LiveData<PagedList<PullRequest>> {
        val segments = repoPath.split("/");
        if (segments.size < 2) {
            toastMessageLiveData.value = R.string.invalid_input
            return MutableLiveData<PagedList<PullRequest>>()
        } else {
            if(dataSourceFactory == null) {
                dataSourceFactory = DataSourceFactory(this, segments[0], segments[1])
                dataSourceFactory?.let {
                    prDataSource = it.create()
                    openPullRequestLiveData = LivePagedListBuilder(it, 20)
                        .build()
                    return openPullRequestLiveData
                }
            }
            return MutableLiveData<PagedList<PullRequest>>()
            //fetchOpenPullRequests(segments[0], segments[1], 30)
        }
    }
}