package io.github.chandilsachin.prapisample.network

import io.github.chandilsachin.prapisample.modules.pr.PullRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("repos/{owner}/{repo}/pulls")
    fun fetchPRs(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Deferred<List<PullRequest>>
}