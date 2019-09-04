package com.coderlab.dagger2dfm.ui.extension

import androidx.fragment.app.Fragment

fun Fragment.coreComponent() = requireActivity().coreComponent()