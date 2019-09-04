package com.coderlab.dagger2dfm

import android.app.Application
import com.coderlab.dagger2dfm.core.di.ContextModule
import com.coderlab.dagger2dfm.core.di.CoreComponent
import com.coderlab.dagger2dfm.core.di.CoreComponentProvider
import com.coderlab.dagger2dfm.core.di.DaggerCoreComponent


class MainApplication : Application(), CoreComponentProvider {
    override fun provideCoreComponent(): CoreComponent = coreComponent
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder().contextModule(ContextModule(this)).build()
    }
}