package com.example.travelsaathi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {

    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var signupRedirectText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        loginUsername = findViewById(R.id.login_username)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
        signupRedirectText = findViewById(R.id.signupRedirectText)

        loginButton.setOnClickListener {
            if (validateUsername() && validatePassword()) {
                checkUser()
            }
        }

        signupRedirectText.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun validateUsername(): Boolean {
        val usernameInput = loginUsername.text.toString()
        return if (usernameInput.isEmpty()) {
            loginUsername.error = "Username cannot be empty"
            false
        } else {
            loginUsername.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = loginPassword.text.toString()
        return if (passwordInput.isEmpty()) {
            loginPassword.error = "Password cannot be empty"
            false
        } else {
            loginPassword.error = null
            true
        }
    }

    private fun checkUser() {
        val userUsername = loginUsername.text.toString().trim()
        val userPassword = loginPassword.text.toString().trim()

        // Retrieve user info from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val storedPassword = sharedPreferences.getString("${userUsername}_password", null)

        // Check if username exists and password matches
        if (storedPassword != null && storedPassword == userPassword) {
            val userName = sharedPreferences.getString("${userUsername}_name", "")
            val userEmail = sharedPreferences.getString("${userUsername}_email", "")

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            // Proceed to MainActivity with user details
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("name", userName)
                putExtra("email", userEmail)
                putExtra("username", userUsername)
            }
            startActivity(intent)
        } else {
            loginPassword.error = "Invalid Credentials"
            loginPassword.requestFocus()
        }
    }
}
