package io.github.chandilsachin.prapisample.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides

//@Module
class ContextModule(var context: Context) {

//    @Provides
    fun provides(): Context {
        return context;
    }
}