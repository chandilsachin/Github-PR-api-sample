package io.github.chandilsachin.prapisample.modules.pr

import android.arch.lifecycle.ViewModel
import io.github.chandilsachin.prapisample.repositories.RemoteRepository
import javax.inject.Inject


class PRViewModel @Inject constructor(var remoteRepository: RemoteRepository): ViewModel() {
}