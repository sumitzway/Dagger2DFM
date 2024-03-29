package com.coderlab.dagger2dfm.core.ui.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


inline fun <reified V : ViewModel> Fragment.getViewModel(
    key: String? = null,
    noinline factory: () -> V
): V {
    @Suppress("UNCHECKED_CAST")
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = factory() as T
    }
    return if (key != null) {
        ViewModelProviders.of(this, viewModelProviderFactory).get(key, V::class.java)
    } else {
        ViewModelProviders.of(this, viewModelProviderFactory).get(V::class.java)
    }
}