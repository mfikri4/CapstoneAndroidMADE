package com.mfikri.capstoneandroidexpert.favorite.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mfikri.capstoneandroidexpert.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fAdapter = FavPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = fAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }
}