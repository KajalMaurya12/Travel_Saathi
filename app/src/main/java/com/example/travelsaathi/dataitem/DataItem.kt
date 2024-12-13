package com.example.travel.dataitem

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class DataItem(
    val viewType: Int,
    var recyclerItemList: List<RecyclerItem>? = null,
    var banner: Banner? = null
) {
    // Firebase reference initialization
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    init {
        // Fetch data if recyclerItemList is expected
        if (recyclerItemList != null) {
            //retrieveItemsFromDatabase()
        }
    }

    // Firebase: Method to retrieve items from the database
    fun retrieveItemsFromDatabase(onItemsRetrieved: (List<RecyclerItem>) -> Unit) {
        val itemList = ArrayList<RecyclerItem>()

        databaseReference.child("Menu").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(AllMenu::class.java)
                    item?.let {
                        val recyclerItem = RecyclerItem(
                            image=it.image3,
                            place = it.place2,
                            placeName = it.place_name2,
                            rating = "4.5",
                            description = it.description,
                            date = "",
                            rate = "",
                            place_photo = "",
                            //place_photo = "",
                            img11 = 0,
                            img22 = 0,
                            img33 = 0,
                            img44 = 0,
                            img55 = 0,
                            img66 = 0,
                            top_text = "",
                            top_image =it.image3
                        )
                        itemList.add(recyclerItem)
                    }
                }
                // Add the itemList to your adapter or RecyclerView here
                //recyclerItemList = itemList
                recyclerItemList = itemList
                onItemsRetrieved(itemList)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError", "Error ${error.message}")
            }
        })
    }

    constructor(viewType: Int, banner: Banner) : this(viewType) {
        this.banner = banner
    }
}

data class RecyclerItem(

    val image: String,
    val place: String,
    val placeName: String,
    val rating: String,
    val description: String,
    val date: String,
    val rate: String,
    val place_photo: String,
    val img11: Int,
    val img22: Int,
    val img33: Int,
    val img44: Int,
    val img55: Int,
    val img66: Int,
    val top_text: String,
    val top_image: String
)

data class Banner(val image: Int)

// Updated model class for Firebase data retrieval
data class AllMenu(
    val image3: String = "",  // Change image3 to String
    val place2: String = "",
    val place_name2: String = "",
    val description: String = ""
)
{
    // Function to safely convert the string to an integer
    fun getImageAsInt(): Int {
        return image3.toIntOrNull() ?: 0
    }
}