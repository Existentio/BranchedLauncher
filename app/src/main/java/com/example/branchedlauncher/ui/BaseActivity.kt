package com.example.branchedlauncher.ui

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import com.example.branchedlauncher.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }






}