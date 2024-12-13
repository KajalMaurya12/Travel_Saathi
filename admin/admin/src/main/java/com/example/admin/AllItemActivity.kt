package com.example.admin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.admin.adapter.MenuItemAdapter
import com.example.admin.databinding.ActivityAllItemBinding
import com.example.admin.modul.AllMenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList

class AllItemActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private  var menuItem: ArrayList<AllMenu> =ArrayList()
    private val binding:ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        databaseReference=FirebaseDatabase.getInstance().reference
        retrieveMenuItem()
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun retrieveMenuItem() {
        database=FirebaseDatabase.getInstance()
        val travelRef:DatabaseReference=database.reference.child("Menu")
        //val travelRef:DatabaseReference=database.reference.child("Menu").child("latest_trip")

        travelRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItem.clear()
                for (travelSnapshot in snapshot.children) {
                    val menuItemData = travelSnapshot.getValue(AllMenu::class.java)
                    //correent writin code
                    menuItemData?.key = travelSnapshot.key // Save the key for deletion
                    if (menuItemData != null) {
                        menuItem.add(menuItemData)
                    }

                    //end code
//                    menuItemData?.let {
//                        menuItem.add(it) // Now it's clear that you are adding to the class scope list
//                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError","Error ${error.message}")
            }
        })
    }
    private fun setAdapter() {
        val adapter=MenuItemAdapter(this,menuItem,databaseReference
        )
        binding.MenuRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.MenuRecyclerView)
    }
}