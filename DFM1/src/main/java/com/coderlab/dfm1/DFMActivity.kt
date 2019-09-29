package com.coderlab.dfm1

import android.os.Bundle
import com.coderlab.dagger2dfm.base.BaseSplitActivity

class DFMActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dfm_activity_main)
    }


}