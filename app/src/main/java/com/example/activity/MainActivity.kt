package com.example.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydemo.databinding.ActivityMainBinding

/**
 *@package com.example.activity
 *@author https://github.com/asd3590058
 *@fileName MainActivity
 *@date 2022/11/8 11:13
 *@description
 */
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}