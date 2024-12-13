package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.travelsaathi.R
import com.example.travelsaathi.book_ticket

class firebase_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_detail)
        val button = findViewById<Button>(R.id.button)
        val placeName = intent.getStringExtra("placeName")
        val description = intent.getStringExtra("description")
         val top_image = intent.getStringExtra("top_image")

        // Find views and set data
        val placeNameTextView: TextView = findViewById(R.id.Place_name)
        val descriptionTextView: TextView = findViewById(R.id.description)
        val top_image1=findViewById<ImageView>(R.id.top_image)



        placeNameTextView.text = placeName
        descriptionTextView.text = description
        Glide.with(this).load(top_image).into(top_image1)



        button.setOnClickListener {
            val intent = Intent(this, book_ticket::class.java)
            startActivity(intent)
        }
    }

}