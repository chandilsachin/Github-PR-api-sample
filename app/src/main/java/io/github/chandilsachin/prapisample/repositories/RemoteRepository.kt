package io.github.chandilsachin.prapisample.repositories

import io.github.chandilsachin.prapisample.modules.pr.PullRequest
import io.github.chandilsachin.prapisample.network.GithubService
import javax.inject.Inject


class RemoteRepository @Inject constructor(private var githubService: GithubService) {

    suspend fun fetchOpenPRs(owner: String, repo: String): List<PullRequest> {
        return githubService.fetchPRs(owner, repo).await()
    }
}