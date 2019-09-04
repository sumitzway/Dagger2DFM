package com.coderlab.dagger2dfm.ui.extension

import android.app.Activity
import com.coderlab.dagger2dfm.core.di.CoreComponentProvider


fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")