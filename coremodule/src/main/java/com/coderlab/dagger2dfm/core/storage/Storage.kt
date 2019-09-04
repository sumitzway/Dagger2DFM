package com.coderlab.dagger2dfm.core.storage

import android.content.Context
import android.util.Log

class Storage(context: Context) {
    init {
        Log.e("dagger", "${this.javaClass.simpleName} > $context}")
        Log.e("dagger", "${this}")
    }
}