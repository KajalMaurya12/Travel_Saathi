package com.example.travel.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travel.Detail_Activity
import com.example.travel.firebase_detail

//import com.example.travel.databinding.IndividualPlaceBinding
//import com.example.travel.databinding.PopularPackageBinding
import com.example.travel.dataitem.DataItemType
import com.example.travel.dataitem.RecyclerItem
import com.example.travelsaathi.databinding.IndividualPlaceBinding
import com.example.travelsaathi.databinding.PopularPackageBinding

@Suppress("DEPRECATION")
class ChildAdapter(
    private val viewType: Int,
    private val context: Context,
    private val recyclerItemList: List<RecyclerItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //chatgpt firebase
    inner class latest_tripViewHolder(private val binding: PopularPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPopular_pacakageView(recyclerItem: RecyclerItem) {
            Glide.with(context)
                .load(recyclerItem.image)
                .into(binding.PopularPackageImage)
            binding.place.text = recyclerItem.place
            binding.PlaceName.text = recyclerItem.placeName
            binding.rating.text = recyclerItem.rating

            if (position == 0) {
                binding.topview.visibility = View.VISIBLE
                binding.topview.text ="Letest Trip" // Set the text if visible
            } else {
                binding.topview.visibility = View.GONE
            }

            itemView.setOnClickListener {
                val intent = Intent(context, firebase_detail::class.java).apply {
                    Glide.with(context)
                        .load(recyclerItem.image)
                        .into(binding.PopularPackageImage)

                    putExtra("placeName", recyclerItem.placeName)
                    putExtra("rating", recyclerItem.rating)
                    putExtra("description", recyclerItem.description)
                    putExtra("date", recyclerItem.date)
                    putExtra("rate", recyclerItem.rate)
                    putExtra("place_photo", recyclerItem.place_photo)
                    putExtra("top_image", recyclerItem.top_image)
                }
                context.startActivity(intent)
            }

        }

    }
    //end chatgpt

    inner class Popular_PackageViewHolder(private val binding: PopularPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPopular_pacakageView(recyclerItem: RecyclerItem) {
            Glide.with(context)
                .load(recyclerItem.image)  // Load the image from a URL
                .into(binding.PopularPackageImage)
            //binding.PopularPackageImage.setImageResource(recyclerItem.image)
            binding.place.text = recyclerItem.place
            binding.PlaceName.text = recyclerItem.placeName
            binding.rating.text = recyclerItem.rating
            if (position == 0) {
                binding.topview.visibility = View.VISIBLE
                binding.topview.text = recyclerItem.top_text // Set the text if visible
            } else {
                binding.topview.visibility = View.GONE
            }
            itemView.setOnClickListener {
                val intent = Intent(context, Detail_Activity::class.java).apply {
                    Glide.with(context)
                        .load(recyclerItem.top_image)
                        .into(binding.PopularPackageImage)
                    putExtra("placeName", recyclerItem.placeName)
                    putExtra("rating", recyclerItem.rating)
                    putExtra("description", recyclerItem.description)
                    putExtra("date", recyclerItem.date)
                    putExtra("rate", recyclerItem.rate)
                    putExtra("place_photo", recyclerItem.place_photo)
                    putExtra("top_image", recyclerItem.top_image)
                    putExtra("img11", recyclerItem.img11)
                    putExtra("img22", recyclerItem.img22)
                    putExtra("img33", recyclerItem.img33)
                    putExtra("img44", recyclerItem.img44)
                    putExtra("img55", recyclerItem.img55)
                    putExtra("img66", recyclerItem.img66)
                }
                context.startActivity(intent)
            }

        }

    }

    inner class Individual_placeViewHolder(private val binding: IndividualPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindIndividual_placeView(recyclerItem: RecyclerItem) {
            Glide.with(context)
                .load(recyclerItem.image)  // Load the image from a URL
                .into(binding.individualImage)
           // binding.individualImage.setImageResource(recyclerItem.image)
            binding.individualText.text = recyclerItem.place
            binding.PlaceName.text = recyclerItem.placeName
            binding.rating2.text = recyclerItem.rating
            if (position == 0) {
                binding.topview.visibility = View.VISIBLE
                binding.topview.text = recyclerItem.top_text // Set the text if visible
            } else {
                binding.topview.visibility = View.GONE
            }

            itemView.setOnClickListener {
                val intent = Intent(context, Detail_Activity::class.java).apply {
                    putExtra("placeName", recyclerItem.placeName)
                    putExtra("rating", recyclerItem.rating)
                    putExtra("description", recyclerItem.description)
                    putExtra("date", recyclerItem.date)
                    putExtra("rate", recyclerItem.rate)
                    putExtra("place_photo", recyclerItem.place_photo)
                    putExtra("top_image", recyclerItem.top_image)
                    putExtra("img11", recyclerItem.img11)
                    putExtra("img22", recyclerItem.img22)
                    putExtra("img33", recyclerItem.img33)
                    putExtra("img44", recyclerItem.img44)
                    putExtra("img55", recyclerItem.img55)
                    putExtra("img66", recyclerItem.img66)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.Popular_Package -> {
                val binding = PopularPackageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return Popular_PackageViewHolder(binding)
            }
//            //chatgpt Firebase
            DataItemType.latest_trip -> {
                val binding = PopularPackageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return latest_tripViewHolder(binding)
            }
            //End Firebase
            else -> {
                val binding = IndividualPlaceBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return Individual_placeViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is Popular_PackageViewHolder -> {
                holder.bindPopular_pacakageView(recyclerItemList[position])
            }

            is Individual_placeViewHolder -> {
                holder.bindIndividual_placeView(recyclerItemList[position])
            }
            // chatgpt Firebase
            is latest_tripViewHolder ->{
                holder.bindPopular_pacakageView(recyclerItemList[position])
            }
            //End Firebase
        }
    }
}


