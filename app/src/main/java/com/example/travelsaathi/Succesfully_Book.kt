package com.example.travelsaathi

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Succesfully_Book : AppCompatActivity() {
    var media2: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_succesfully_book)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dailogbox)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        media2 = MediaPlayer.create(this@Succesfully_Book, R.raw.successound)
        media2?.start()

        // Handler to dismiss the dialog and pause the sound after 2 seconds
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            dialog.dismiss()
            media2?.pause()
        }, 2000)

        // Handler to navigate to MainActivity after 10 seconds
        handler.postDelayed({
            val intent = Intent(this@Succesfully_Book, MainActivity::class.java)
            startActivity(intent)
            finish()  // Finish the current activity so it's removed from the back stack
        }, 4000) // 10-second delay
    }

    override fun onDestroy() {
        super.onDestroy()
        media2?.release()  // Release the MediaPlayer resources
    }
}
