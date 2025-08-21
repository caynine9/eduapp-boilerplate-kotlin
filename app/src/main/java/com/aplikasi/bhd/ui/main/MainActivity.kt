package com.aplikasi.bhd.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.bhd.databinding.ActivityMainBinding
import com.aplikasi.bhd.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView.id, homeFragment)
            commit()
        }
    }
}