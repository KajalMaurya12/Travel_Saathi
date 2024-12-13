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

class Detail_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        val button = findViewById<Button>(R.id.button)
        val placeName = intent.getStringExtra("placeName")
        val rating = intent.getStringExtra("rating")
        val description = intent.getStringExtra("description")
        val date=intent.getStringExtra("date")
        val rate=intent.getStringExtra("rate")
        val place_photo=intent.getStringExtra("place_photo")
        val img11 = intent.getIntExtra("img11", 0)
        val img22 = intent.getIntExtra("img22", 0)
        val img33 = intent.getIntExtra("img33", 0)
        val img44 = intent.getIntExtra("img44", 0)
        val img55 = intent.getIntExtra("img55", 0)
        val img66 = intent.getIntExtra("img66", 0)
        val top_image = intent.getStringExtra("top_image")

        // Find views and set data
        val placeNameTextView: TextView = findViewById(R.id.Place_name)
        val ratingTextView: TextView = findViewById(R.id.rating)
        val descriptionTextView: TextView = findViewById(R.id.description)
        val travel_date =findViewById<TextView>(R.id.date)
        val price=findViewById<TextView>(R.id.rate)
        val place_photo1=findViewById<TextView>(R.id.place_photo)
        val img1=findViewById<ImageView>(R.id.img11)
        val img2=findViewById<ImageView>(R.id.img22)
        val img3=findViewById<ImageView>(R.id.img33)
        val img4=findViewById<ImageView>(R.id.img44)
        val img5=findViewById<ImageView>(R.id.img55)
        val img6=findViewById<ImageView>(R.id.img66)
        val top_image1=findViewById<ImageView>(R.id.top_image)




        placeNameTextView.text = placeName
        ratingTextView.text = rating
        descriptionTextView.text = description
        travel_date.text=date
        price.text=rate
        place_photo1.text=place_photo
        Glide.with(this).load(img11).into(img1)
        Glide.with(this).load(img22).into(img2)
        Glide.with(this).load(img33).into(img3)
        Glide.with(this).load(img44).into(img4)
        Glide.with(this).load(img55).into(img5)
        Glide.with(this).load(img66).into(img6)
        Glide.with(this).load(top_image).into(top_image1)



        button.setOnClickListener {
            val intent = Intent(this, book_ticket::class.java)
            startActivity(intent)
        }
    }
}