package io.github.chandilsachin.prapisample.network

import io.github.chandilsachin.prapisample.modules.pr.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun fetchPRs(@Path("owner") owner: String,
                         @Path("repo") repo: String): List<PullRequest>
}