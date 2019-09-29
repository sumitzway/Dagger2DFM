package com.coderlab.dagger2dfm

import android.app.Application
import android.content.Context
import com.coderlab.dagger2dfm.core.di.ContextModule
import com.coderlab.dagger2dfm.core.di.CoreComponent
import com.coderlab.dagger2dfm.core.di.CoreComponentProvider
import com.coderlab.dagger2dfm.core.di.DaggerCoreComponent
import com.coderlab.dagger2dfm.helper.LanguageHelper
import com.google.android.play.core.splitcompat.SplitCompat


class MainApplication : Application(), CoreComponentProvider {
    override fun provideCoreComponent(): CoreComponent = coreComponent
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder().contextModule(ContextModule(this)).build()
    }

    override fun attachBaseContext(base: Context) {
        LanguageHelper.init(base)
        val ctx = LanguageHelper.getLanguageConfigurationContext(base)
        super.attachBaseContext(ctx)
        SplitCompat.install(this)
    }
}