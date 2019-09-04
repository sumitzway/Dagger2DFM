package com.coderlab.dagger2dfm.core.di

import android.content.Context
import com.coderlab.dagger2dfm.core.storage.Storage
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, ContextModule::class])
interface CoreComponent {
    fun context(): Context
    fun storage(): Storage
}