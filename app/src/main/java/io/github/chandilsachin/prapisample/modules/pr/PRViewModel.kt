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
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject


class PRViewModel @Inject constructor(var remoteRepository: RemoteRepository) : ViewModel() {

    private val job = Job()
    val loading: ObservableBoolean = ObservableBoolean(false)
    val messageVisibility: ObservableInt = ObservableInt(View.VISIBLE)
    val message: ObservableInt = ObservableInt(R.string.search_pr_message)
    val toastMessageLiveData = MutableLiveData<Int>()
    val openPullRequestLiveData = MutableLiveData<List<PullRequest>>()

    fun fetchOpenPullRequests(owner: String, repo: String) {
        loading.set(true)
        var list: List<PullRequest>
        GlobalScope.launch {
            try {
                list = remoteRepository.fetchOpenPRs(owner, repo)
                withContext(Dispatchers.Main) {
                    if (list.isNotEmpty()) {
                        openPullRequestLiveData.value = list
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
        }
    }


    fun onClickSearchButton(repoPath: String){
        val segments = repoPath.split("/");
        if (segments.size < 2) {
            toastMessageLiveData.value = R.string.invalid_input
        } else {
            fetchOpenPullRequests(segments[0], segments[1])
        }
    }
}