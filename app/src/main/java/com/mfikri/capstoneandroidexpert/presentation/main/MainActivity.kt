package com.mfikri.capstoneandroidexpert.presentation.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.mfikri.capstoneandroidexpert.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fAdapter = MainPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = fAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f

        binding.btnFav.setOnClickListener {
            val mIntent = Intent(
                this,
                Class.forName("com.mfikri.capstoneandroidexpert.favorite.presentation.FavoriteActivity")
            )
            startActivity(mIntent)
        }

        binding.btnSetting.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

    }

}