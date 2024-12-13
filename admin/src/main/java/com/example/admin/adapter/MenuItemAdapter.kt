package com.example.admin.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.MainActivity
import com.example.admin.R
import com.example.admin.databinding.ItemItemBinding
import com.example.admin.modul.AllMenu
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

//add item first here
class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    //Firebase
    private val databaseReference: DatabaseReference
    //End Firebase
):RecyclerView.Adapter<MenuItemAdapter.AddItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size


    //second code of add item
    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val menuItem = menuList[position]
                val uriString = menuItem.image3
                val uri= Uri.parse(uriString)
                Glide.with(context).load(uri).into(image)
                place.text = menuItem.place2
                PlaceName.text = menuItem.place_name2

//                delete.setOnClickListener {
//
//                    menuList.removeAt(position)
////                    menuList.removeAt(position)
////                    menuList.removeAt(position)
//
//                    notifyItemRemoved(position)
//                    notifyItemRangeChanged(position,menuList.size)
////
//
//               }
                //fIREBASE
                delete.setOnClickListener {
                    deleteFromDatabase(menuItem.key!!, position) // Correct deletion method
                }
                //end firebase

            }

            }
        }

    private fun deleteFromDatabase(itemKey: String, position: Int) {
        val reference = FirebaseDatabase.getInstance().getReference("Menu")
        reference.child(itemKey).removeValue().addOnSuccessListener {
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, menuList.size)
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to delete data", Toast.LENGTH_SHORT).show()
        }

    }
}
