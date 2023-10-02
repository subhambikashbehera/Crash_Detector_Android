package com.subhambikash.crashdetector

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.subhambikash.crashdetector.databinding.ActivityCrashBinding

class CrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crash)
        val errorDetails = intent.getStringExtra("errorDeatils")
        Log.d("checkErrorDetails", "onCreate: $errorDetails")

        if (errorDetails != null) {
            checkAppIsDevelopment(errorDetails)
        }

        binding.btRestartApp.setOnClickListener {
            restartApp()
        }
    }


    private fun restartApp() {
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }

    private fun checkAppIsDevelopment(errorDetails: String) {
        // this logic is written for the to check the app is in debug mode or release mode
        val isInDebugApp = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        if (isInDebugApp) {
            binding.tvErrorLogs.isVisible = true
            binding.tvErrorLogs.text = errorDetails
        }
    }

    override fun onBackPressed() {
        // nothing we will do on back pressed
    }

}