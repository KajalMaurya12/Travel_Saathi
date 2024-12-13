package com.example.travelsaathi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class book_ticket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ticket)

        val btn_submit = findViewById<Button>(R.id.btn_submit)
        val passengerName = findViewById<EditText>(R.id.et_passenger_name)
        val phone = findViewById<EditText>(R.id.et_phone)
        val address = findViewById<EditText>(R.id.et_address)
        val email = findViewById<EditText>(R.id.et_email)
        val totalAdults = findViewById<EditText>(R.id.et_total_adults)
        val totalChildren = findViewById<EditText>(R.id.et_total_children)
        val typeOfTravel = findViewById<Spinner>(R.id.spinner_type_of_travel)
        val destination = findViewById<EditText>(R.id.et_destination)
        val preferredHotel = findViewById<Spinner>(R.id.spinner_preferred_hotel)
        val numberOfRooms = findViewById<EditText>(R.id.et_number_of_rooms)
        val sizeOfGroup = findViewById<EditText>(R.id.et_size_of_group)
        val transportationGroup = findViewById<RadioGroup>(R.id.rg_transportation)
        val tripAmount = findViewById<EditText>(R.id.et_trip_amount)
        val submitButton = findViewById<Button>(R.id.btn_submit)

        val travelOptions = arrayOf("Bus", "Cab", "Airplane")
        val hotelOptions = arrayOf("Hotel A", "Hotel B", "Hotel C")

        val travelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, travelOptions)
        travelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeOfTravel.adapter = travelAdapter

        val hotelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hotelOptions)
        hotelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        preferredHotel.adapter = hotelAdapter

        submitButton.setOnClickListener {
            if (validateFields(passengerName, phone, address, email, totalAdults, totalChildren,
                    destination, numberOfRooms, sizeOfGroup, tripAmount)) {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
            }
        }


        btn_submit.setOnClickListener {
            val intent = Intent(this, Succesfully_Book::class.java)
            startActivity(intent)
        }
    }

    private fun validateFields(vararg fields: EditText): Boolean {
        for (field in fields) {
            if (TextUtils.isEmpty(field.text.toString().trim())) {
                field.error = "${field.hint} is required"
                field.requestFocus()
                return false
            }
        }
        return true
    }


}
