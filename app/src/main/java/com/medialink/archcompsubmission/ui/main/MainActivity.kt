package com.medialink.archcompsubmission.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.medialink.archcompsubmission.R
import com.medialink.archcompsubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        with (activityMainBinding) {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(activityMainBinding.viewPager)
        }

        supportActionBar?.elevation = 0f
    }
}