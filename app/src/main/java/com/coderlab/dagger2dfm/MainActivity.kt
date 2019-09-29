package com.coderlab.dagger2dfm


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.coderlab.dagger2dfm.base.BaseSplitActivity
import com.coderlab.dagger2dfm.core.storage.Storage
import com.coderlab.dagger2dfm.databinding.MainActivityBinding
import com.coderlab.dagger2dfm.di.DaggerMainActivityComponent
import com.coderlab.dagger2dfm.extensions.getObjectKey
import com.coderlab.dagger2dfm.helper.DFMHelper
import com.coderlab.dagger2dfm.twowaydatabinding.TwoWayDataBindingModel
import com.coderlab.dagger2dfm.ui.extension.coreComponent
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseSplitActivity() {

    private val REQ_MODULE_DOWNLOAD = 45

    @Inject
    lateinit var storage: Storage

    val feature1Name: String by lazy {
        resources.getString(R.string.feature1_name)
    }

    val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencyInjection()
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = TwoWayDataBindingModel()



        Log.e("storage", "MainActivity > " + toString())
        Log.e("storage", "MainActivity > " + System.identityHashCode(this))
        Log.e("storage", "MainActivity > " + this.getObjectKey())

        main_text.setOnClickListener {
            if (manager.installedModules.contains(feature1Name)) {
                onSuccessfulLoad(feature1Name, launch = true)
                return@setOnClickListener
            }
            // Create request to install a feature module by name.
            val request = SplitInstallRequest.newBuilder()
                .addModule(feature1Name)
                .build()

            // Load and install the requested feature module.
            manager.startInstall(request)
        }

    }


    val installListener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val langsInstall = state.languages().isNotEmpty()

        val names = if (langsInstall) {
            // We always request the installation of a single language in this sample
            state.languages().first()
        } else state.moduleNames().joinToString(" - ")

        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                Log("SplitInstallSessionStatus.DOWNLOADING $state")
                //  In order to see this, the application has to be uploaded to the Play Store.
                displayLoadingState(state, "Downloading $names")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                Log("SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION $state")
                /*
                  This may occur when attempting to download a sufficiently large module.
                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
                manager.startConfirmationDialogForResult(state, this, REQ_MODULE_DOWNLOAD)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                Log("SplitInstallSessionStatus.INSTALLED $state")
                onSuccessfulLoad(names, launch = !multiInstall)
            }
            SplitInstallSessionStatus.INSTALLING -> {
                displayLoadingState(state, "INSTALLING $names")
                Log("SplitInstallSessionStatus.DOWNLOADING $state")
            }
            SplitInstallSessionStatus.FAILED -> {
                Log("SplitInstallSessionStatus.FAILED $state")
                toastAndLog("error code ${state.errorCode()} of module ${state.moduleNames()}")
            }
        }
    }

    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                feature1Name -> {
                    Intent().also {
                        it.setClassName(this, DFMHelper.Feature1.homeScreen)
                        startActivity(it)
                    }
                }
            }
        }
    }


    /** Display a loading state to the user. */
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()
        progressBar.max = state.totalBytesToDownload().toInt()
        progressBar.progress = state.bytesDownloaded().toInt()
        updateProgressMessage(message)
    }

    private fun updateProgressMessage(message: String) {
        if (progressBar.visibility != View.VISIBLE) displayProgress()
        progressText.text = message
    }

    /** Display progress bar and text. */
    private fun displayProgress() {
        progressBar.visibility = View.VISIBLE
        main_text.visibility = View.GONE
    }

    private fun initDependencyInjection() =
        DaggerMainActivityComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)

    override fun onResume() {
        // Listener can be registered even without directly triggering a download.
        manager.registerListener(installListener)
        super.onResume()
    }

    override fun onPause() {
        // Make sure to dispose of the listener once it's no longer needed.
        manager.unregisterListener(installListener)
        super.onPause()
    }
}


fun MainActivity.toastAndLog(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    Log.d("MainActivity", text)
}

fun MainActivity.Log(text: String) {
    Log.d("MainActivity", text)
}