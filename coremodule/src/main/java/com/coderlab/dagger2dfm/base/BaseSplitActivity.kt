package com.coderlab.dagger2dfm.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.coderlab.dagger2dfm.helper.LanguageHelper
import com.google.android.play.core.splitcompat.SplitCompat

abstract class BaseSplitActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        val ctx = newBase?.let { LanguageHelper.getLanguageConfigurationContext(it) }
        super.attachBaseContext(ctx)
        SplitCompat.install(this)
    }

}