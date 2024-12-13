package com.example.admin

import android.content.ClipDescription
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.admin.databinding.ActivityAddItem2Binding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.modul.AllMenu
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class AddItemActivity : AppCompatActivity() {


    //Food Item Detail
    private var image:Uri?=null
    private lateinit var place2:String
    private lateinit var place_name2:String
    private lateinit var description: String

    //Firebase
    private lateinit var auth : FirebaseAuth
    private lateinit var database:FirebaseDatabase

    private val binding: ActivityAddItem2Binding by lazy {
        ActivityAddItem2Binding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()

        binding.AddItemButton.setOnClickListener {
            place2=binding.place2.text.toString().trim()
            place_name2=binding.place2.text.toString().trim()
            description=binding.description.text.toString().trim()

            if (!(place2.isBlank() || place_name2.isBlank() || description.isBlank())){
                uploadData()
                Toast.makeText(this, "Item Added Sucessfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Fill all tha detail", Toast.LENGTH_SHORT).show()
            }

        }
        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.backButton.setOnClickListener{
            finish()
        }
    }

    private fun uploadData() {
        val menuRef=database.getReference("Menu")
        val newItemKey:String?=menuRef.push().key
        if (image != null){
            val storageRef=FirebaseStorage.getInstance().reference
            val imageRef=storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask=imageRef.putFile(image!!)
            //Firebase

            //End Firebase

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl ->
                    val newItem=AllMenu(
                        place2=place2,
                        place_name2 = place_name2,
                        description = description,
                        image3 = downloadUrl.toString()
                    )
                    newItemKey?.let {
                        key ->
                       menuRef.child(key).setValue(newItem).addOnSuccessListener{
//                        menuRef.child("latest_trip").child(key).setValue(newItem).addOnSuccessListener{
                            Toast.makeText(this, "data upload successfully", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this, "data upload failed", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }
                .addOnFailureListener {
                    Toast.makeText(this, "image upload failed", Toast.LENGTH_SHORT).show()
                }
        }
            else {
                Toast.makeText(this, "please add image", Toast.LENGTH_SHORT).show()
            }
    }

    private val pickImage=registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if (uri != null)
        {
            binding.selectedImage.setImageURI(uri)
            image=uri
        }
    }

}



