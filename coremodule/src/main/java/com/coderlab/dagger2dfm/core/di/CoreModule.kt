package com.coderlab.dagger2dfm.core.di

import android.content.Context
import com.coderlab.dagger2dfm.core.storage.Storage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class CoreModule {

    @Provides
    @Singleton // we can use @Reusable in place of @Singleton if accessing is not frequent
    fun provideStorage(context: Context) = Storage(context)
}