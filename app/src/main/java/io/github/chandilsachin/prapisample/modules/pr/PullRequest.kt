package io.github.chandilsachin.prapisample.modules.pr

data class PullRequest
    (
    val id: Int,
    val title: String,
    val user: User,
    val number: Int,
    val status: String,
    val created_at: String,
    val updated_at: String,
    var page: Int
)