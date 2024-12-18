package com.example.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.addMenu.setOnClickListener{
            val intent = Intent(this,AddItemActivity::class.java)
            startActivity(intent)
        }
        binding.AllItemMenu.setOnClickListener{
            val intent = Intent(this,AllItemActivity::class.java)
            startActivity(intent)
        }
    }
}


