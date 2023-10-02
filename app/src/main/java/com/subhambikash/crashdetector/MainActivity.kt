package com.subhambikash.crashdetector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.subhambikash.crashdetector.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        handleUncaughtException()


        CoroutineScope(Dispatchers.Main).launch {
            val customString = "subham"
            for (i in 1..6) {
                // setting count on the text view
                binding.tvCount.text = i.toString()
                delay(1000)
                // when the count is 6 we will make one crash
                if (i == 6) {
                    customString[7]
                }
            }
        }
    }

}