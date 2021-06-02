package com.mfikri.capstoneandroidexpert.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mfikri.capstoneandroidexpert.databinding.ActivitySplashScreen2Binding
import com.mfikri.capstoneandroidexpert.presentation.main.MainActivity

class SplashScreenActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivNext.setOnClickListener {
            val mIntent = Intent(this@SplashScreenActivity2, MainActivity::class.java)
            startActivity(mIntent)
        }
    }
}