package io.github.chandilsachin.prapisample.dagger.modules

import dagger.Module
import dagger.Provides
import io.github.chandilsachin.prapisample.network.GithubService
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import javax.inject.Inject

@Module
class RepositoryModule() {

    @Provides
    @Inject
    fun providesRemoteRepository(githubService: GithubService): RemoteRepository {
        return RemoteRepository(githubService);
    }
}