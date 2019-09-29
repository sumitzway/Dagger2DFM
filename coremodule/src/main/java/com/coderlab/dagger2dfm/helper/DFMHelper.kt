package com.coderlab.dagger2dfm.helper

object DFMHelper {

    object Feature1 {
        val pakageName: String = "com.coderlab.dfm1"
        val homeScreen: String
            get() = "$pakageName.DFMActivity"

    }
}