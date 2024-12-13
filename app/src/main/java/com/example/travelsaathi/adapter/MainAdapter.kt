package com.example.travel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

import com.example.travel.dataitem.Banner
import com.example.travel.dataitem.DataItem
import com.example.travel.dataitem.DataItemType
import com.example.travel.dataitem.RecyclerItem
import com.example.travelsaathi.R
import com.example.travelsaathi.databinding.BannerItemBinding
import com.example.travelsaathi.databinding.EachItemBinding


class MainAdapter(private val dataItemList: ArrayList<DataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BannerItemViewHolder(private val binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBannerView(banner: Banner) {
            binding.bannerIv.setImageResource(banner.image)
        }
    }

    inner class RecyclerItemViewHolder(private val binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.childRecyclerView.setHasFixedSize(true)
            binding.childRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)

            //firebase
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.childRecyclerView)
            //End Firebase
        }

        //Firebase
        fun bindletest_tripRecyclerView(recyclerItemList: List<RecyclerItem>) {
            val adapter =
                ChildAdapter(DataItemType.latest_trip, binding.root.context, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }
        //End Firebase

        fun bindPopular_PackageRecyclerView(recyclerItemList: List<RecyclerItem>) {
            val adapter =
                ChildAdapter(DataItemType.Popular_Package, binding.root.context, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }

        fun bindIndividual_placeRecyclerView(recyclerItemList: List<RecyclerItem>) {

//            val snapHelper = PagerSnapHelper()
//            snapHelper.attachToRecyclerView(binding.childRecyclerView)
            val adapter =
                ChildAdapter(DataItemType.Individual_place, binding.root.context, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItemList[position].viewType) {
            DataItemType.banner ->
                R.layout.banner_item

            //firebase
            DataItemType.latest_trip ->
                R.layout.latest_trip
//            //End Firebase

            else ->
                R.layout.each_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.banner_item -> {
                val binding =
                    BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }
            //Firebase
            R.layout.latest_trip -> {
                val binding =
                    EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecyclerItemViewHolder(binding)
            }
            // End Firebase
            else -> {
                val binding =
                    EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecyclerItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dataItemList[position].banner?.let { holder.bindBannerView(it) }
            }

            else -> {
                when (dataItemList[position].viewType) {
                    DataItemType.Popular_Package -> {
                            dataItemList[position].recyclerItemList?.let {
                            (holder as RecyclerItemViewHolder).bindPopular_PackageRecyclerView(it)
                        }
                    }

                    //Firebase
                    DataItemType.latest_trip -> {
                        dataItemList[position].recyclerItemList?.let {
                            (holder as RecyclerItemViewHolder).bindletest_tripRecyclerView(it)
                        }
                    }
                    //End Firebase
                        else->{
                            dataItemList[position].recyclerItemList?.let {
                                (holder as RecyclerItemViewHolder).bindIndividual_placeRecyclerView(
                                    it
                                )
                            }
                        }
                    }

                }
            }
        }
    }
