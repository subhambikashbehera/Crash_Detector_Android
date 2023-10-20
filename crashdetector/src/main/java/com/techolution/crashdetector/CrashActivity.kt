package com.techolution.crashdetector

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techolution.crashdetector.databinding.ActivityCrashBinding
import com.techolution.crashdetector.databinding.ErrorLogBottomsheetBinding

class CrashActivity : AppCompatActivity() {

    private var bottomSheetDialog: BottomSheetDialog? = null
    private lateinit var binding: ActivityCrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crash)
        val errorDetails = intent.getStringExtra("errorDetails")
        val isShownLogs = intent.getStringExtra("isShownLogs")
        if (isShownLogs != null) {
            errorLogsButtonVisibility(isShownLogs, errorDetails.isNullOrEmpty())
        }

        binding.btRestartApp.setOnClickListener {
            restartApp()
        }
        binding.btShowErrorLogs.setOnClickListener {
            showBottomSheetDialog(true, errorDetails)
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

    private fun showBottomSheetDialog(state: Boolean, errorData: String? = "No error logs found") {
        if (state) {
            if (bottomSheetDialog != null) {
                if (bottomSheetDialog?.isShowing == true) {
                    bottomSheetDialog?.dismiss()
                }
            }
            bottomSheetDialog = BottomSheetDialog(this, R.style.TransparentDialog)
            val dialogBinding: ErrorLogBottomsheetBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.error_log_bottomsheet, null, false)
            bottomSheetDialog?.setContentView(dialogBinding.root)
            bottomSheetDialog?.setCanceledOnTouchOutside(false)
            bottomSheetDialog?.setCancelable(false)
            dialogBinding.closeDialog.setOnClickListener {
                showBottomSheetDialog(false)
            }
            dialogBinding.tvErrorLogs.text = errorData
            dialogBinding.btCopyErrorLogs.setOnClickListener {
                copyToClipBoard(errorData.toString())
                showBottomSheetDialog(false)
                showToastMessage("Logs copied")
            }

            bottomSheetDialog?.behavior?.expandedOffset = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            bottomSheetDialog?.show()
        } else {
            if (bottomSheetDialog != null) {
                if (bottomSheetDialog?.isShowing == true) {
                    bottomSheetDialog?.dismiss()
                }
            }
        }
    }

    private fun errorLogsButtonVisibility(isShownLogs: String, errorLogsNullOrEmpty: Boolean) {
        if (errorLogsNullOrEmpty) {
            binding.btShowErrorLogs.isVisible = false
        } else {
            when (isShownLogs.lowercase()) {
                "true" -> {
                    binding.btShowErrorLogs.isVisible = true
                }

                "false" -> {
                    binding.btShowErrorLogs.isVisible = false
                }

                "null" -> {
                    checkAppIsDevelopment()
                }
            }


        }
    }

    private fun checkAppIsDevelopment() {
        // this logic is written for the to check the app is in debug mode or release mode
        val isInDebugApp = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        binding.btShowErrorLogs.isVisible = isInDebugApp
    }

}