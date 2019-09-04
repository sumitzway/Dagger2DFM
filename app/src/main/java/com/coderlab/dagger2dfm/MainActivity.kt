package com.coderlab.dagger2dfm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coderlab.dagger2dfm.core.storage.Storage
import com.coderlab.dagger2dfm.di.DaggerMainActivityComponent
import com.coderlab.dagger2dfm.ui.extension.coreComponent


import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencyInjection()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.e("storage", "MainActivity > " + storage.toString())
        main_text.setOnClickListener {
            //startActivity(Intent(this, ActivityTwo::class.java))
        }

    }

    private fun initDependencyInjection() =
        DaggerMainActivityComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)


}
