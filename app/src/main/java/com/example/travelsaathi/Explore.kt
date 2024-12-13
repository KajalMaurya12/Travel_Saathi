package com.example.travelsaathi

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel.adapter.MainAdapter
import com.example.travel.dataitem.Banner
import com.example.travel.dataitem.DataItem
import com.example.travel.dataitem.DataItemType
import com.example.travel.dataitem.RecyclerItem
import com.example.travelsaathi.databinding.FragmentExploreBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*


class Explore: Fragment() {

    private lateinit var binding: FragmentExploreBinding
    private lateinit var mList: ArrayList<DataItem>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(requireContext()) // Initialize Firebase
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //val view = inflater.inflate(R.layout.fragment_explore, container, false)

        // Set up RecyclerView
        binding.mainRecycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference
        mList = ArrayList()

        // Set up adapter
        adapter = MainAdapter(mList)
        binding.mainRecycleView.adapter = adapter

        // Prepare and load data
        prepareData()

        return root

    }

    private fun prepareData() {
        val latestTrip = ArrayList<RecyclerItem>()

        // Firebase data retrieval
        val latestTripReference = databaseReference.child("latest_trip")
        latestTripReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                latestTrip.clear()
                for (dataSnapshot in snapshot.children) {
                    val item = dataSnapshot.getValue(RecyclerItem::class.java)
                    if (item != null) {
                        latestTrip.add(item)
                    }
                }
                // Add the retrieved data to mList
                mList.add(0, DataItem(DataItemType.latest_trip, latestTrip))
                adapter.notifyDataSetChanged() // Notify the adapter of data change
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database errors
            }
        })
        //End Firebase


        val Popular_Package = ArrayList<RecyclerItem>()
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTJlXdZOoGttOVllPjd21HW1BI7PdQkeGLWcMdwTj8n25HJM3t1\n",
                "GOA",
                "Dudhsagar Waterfalls",
                "4.5",
                "If you are looking for a memorable Goa trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Goa tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 127 tour packages to Goa with unbeatable deals and discounts. Explore the main Goa sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Goa Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Goa vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Goa Tour Packages offering the best of hotels and inclusions designed to pamper you on your Goa vacation. If you wish to know more about Goa travel such as the best time to visit Goa, how to reach Goa, things to do in Goa and so on.\n" +
                        "Browse popular Goa Theme Packages like: Goa family packages, Goa adventure packages.\n" +
                        "\n" +
                        "Goa Package\n" +
                        "1.Baga Beach\n" +
                        "2.Calangute Beach\n" +
                        "3.Palolem Beach\n" +
                        "4.Anjuna Beach\n" +
                        "5.Candolim Beach\n" +
                        "6.Vagator Beach\n" +
                        "7.Arambol Beach\n" +
                        "\n" +
                        "Departing on 17 Sep, 10:00 AM | Arriving on 17 Sep, 12:30 PM | Includes Check In Baggage",
                "17 april 2020",
                "$500-$800",
                place_photo = "Popular Package",
                R.drawable.goa1,
                R.drawable.goa2,
                R.drawable.goa3,
                R.drawable.goa4,
                R.drawable.goa5,
                R.drawable.goa6,
                "Popular Package",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTJlXdZOoGttOVllPjd21HW1BI7PdQkeGLWcMdwTj8n25HJM3t1\n",
            )
        )
        //2
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRMgOOJC-pxIFE1xOXjn3uxzdhK4n5FKr6OG0-KzeXJRihDxwkz\n",
                "Andhra Pradesh",
                "Tirupati & Pondicherry",
                "4.5",
                "If you are planning a Andhra Pradesh trip, MakeMyTrip is the right place to come to. Whatever your travel preference, you will find suitable Andhra Pradesh holiday packages here. Whether you are looking for Andhra Pradesh packages for a family or a couple, whether you need escorted Andhra Pradesh tour packages for your parents or an offbeat Andhra Pradesh vacation for yourself, MakeMyTrip can help you sort out your Andhra Pradesh trip plan.\n" +
                        "MakeMyTrip currently offers over 41 tour packages to Andhra Pradesh, with prices starting as low as Rs.10411. Explore a variety of itineraries and choose from Andhra Pradesh travel packages with or without flights. With our unbeatable deals and discounts, your money goes further! Don’t forget to add tours and activities to your selected Andhra Pradesh packages.\n" +
                        "\n" +
                        "We curate our Andhra Pradesh packages by theme as well, to help you find your kind of holiday. Whether you are planning your honeymoon and looking for romantic Andhra Pradesh packages for couples or simply want an adventurous Andhra Pradesh trip with friends, you will find the right choice at MakeMyTrip. Be it a short trip or long, a relaxing stay or an active holiday, whatever your travel style, MakeMyTrip has the right Andhra Pradesh vacation packages for you to choose from. Looking for a luxury holiday? Check out our luxury Andhra Pradesh packages offering the best of hotels and inclusions designed to pamper you on your Andhra Pradesh vacation.\n" +
                        "To help you prepare for your Andhra Pradesh travel, we have put together some tips such as the best time to visit Andhra Pradesh, things to do in Andhra Pradesh and so on.\n" +
                        "\n" +
                        "Goa Package\n" +
                        "1.Peaceful Tirupati & Pondicherry Trip Package\n" +
                        "2.A Spiritual South Vacay Package\n" +
                        "3.Divine Trip to Tirupati, Mahabalipuram & Chennai Package\n" +
                        "\n" +
                        "Departing on 17 Sep, 10:00 AM | Arriving on 17 Sep, 12:30 PM | Includes Check In Baggage",
                "22 october 2020",
                "$900-$800",
                "Popular Package",
                R.drawable.andhra1,
                R.drawable.andhra2,
                R.drawable.andhra3,
                R.drawable.andhra4,
                R.drawable.andhra5,
                R.drawable.andhra6,
                "Popular Package",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRMgOOJC-pxIFE1xOXjn3uxzdhK4n5FKr6OG0-KzeXJRihDxwkz\n",
            )
        )
        //3
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQkWaWHxC52BWbTv_Nq2d-VgK8PEpiN1QrmNtnuDie8A8M0GYcj\n",
                "Arunachal Pradesh",
                "Alluring Meghalaya,Assam",
                "4.5",
                "Arunachal Pradesh\n" +
                        "\n" +
                        "If you wish to explore Arunachal Pradesh tourist places, then you must check out MakeMyTrip’s Arunachal Pradesh tourism packages. MakeMyTrip offers the widest coverage of Arunachal Pradesh tourism places through a variety of holiday packages and activities. Pick from a range of 5 packages starting from Rs.57710. Explore Arunachal Pradesh tourist spots with your friends and family, stay in quality hotels recommended by MakeMyTrip and approved by Arunachal Pradesh tourism, and have a memorable Arunachal Pradesh trip.\n" +
                        "\n" +
                        "Tourism in Arunachal Pradesh offers a variety of sightseeing and entertainment opportunities for all kinds of travellers. Your preferred Arunachal Pradesh tourist place would depend on whether you are travelling with family or friends and your holiday style, but never fear because Arunachal Pradesh tourism has something for everyone.\n" +
                        "\n" +
                        "Arunachal Pradesh Tourism Package 2024\n" +
                        "1.Alluring Meghalaya, Arunachal & Assam Package\n" +
                        "2.Magical Arunachal & Kaziranga Vacation Package\t\n" +
                        "3.Ziro Festival 2024 with Tezpur Package\n" +
                        "4.Ziro Music Festival 2024 Group Tour Package\n" +
                        "5.Relaxing Arunachal Pradesh & Assam Trip Package",
                "20 september 2020",
                "$800-$800",
                "Popular Package",
                R.drawable.arunachal1,
                R.drawable.arunachal2,
                R.drawable.arunachal3,
                R.drawable.arunachal4,
                R.drawable.arunachal5,
                R.drawable.arunachal6,
                "Popular Package",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQkWaWHxC52BWbTv_Nq2d-VgK8PEpiN1QrmNtnuDie8A8M0GYcj\n",
            )
        )
        //4
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRiyoLjJrqvFiS57QrPoS2GxfVDB9QaYXpmx56udUwZD3JFaQQ-\n",
                "Bihar",
                "Varanasi & Bodhgaya",
                "4.5" ,
                "If you are looking for a memorable Bihar trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Bihar tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 9 tour packages to Bihar with unbeatable deals and discounts. Explore the main Bihar sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Bihar Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Bihar vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Bihar Tour Packages offering the best of hotels and inclusions designed to pamper you on your Bihar vacation. If you wish to know more about Bihar travel such as the best time to visit Bihar, how to reach Bihar, things to do in Bihar and so on.\n" +
                        "\n" +
                        "Popular Bihar Tour Packages in 2024\n" +
                        "1.Trip to Varanasi & Bodhgaya Package\t\n" +
                        "2.Pilgrimage to Gorakhpur from Lucknow Package\t\n" +
                        "3.Long weekend to Gorakhpur from Lucknow Package\t\n" +
                        "4.Trip to Varanasi & Bodhgaya Package\t\n" +
                        "5.Pilgrimage to Gorakhpur from Lucknow Package\t\n" +
                        "6.Long weekend to Gorakhpur from Lucknow Package\t",
                "18 april 2020",
                "$500-$800",
                "Popular Package",
                R.drawable.bihar1,
                R.drawable.bihar2,
                R.drawable.bihar3,
                R.drawable.bihar4,
                R.drawable.bihar5,
                R.drawable.bihar6,
                "Popular Package",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRiyoLjJrqvFiS57QrPoS2GxfVDB9QaYXpmx56udUwZD3JFaQQ-\n",
            )
        )
        //5
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRycZIBorWOe6KHgYtHvw57U8uPXw8uzcoGSQfdPjWxQ_kCHvyL\n",
                "Assam",
                "Mawlynnon",
                "4.5",
                "If you are looking for a memorable Assam trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Assam tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 21 tour packages to Assam with unbeatable deals and discounts. Explore the main Assam sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Assam Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Assam vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Assam Tour Packages offering the best of hotels and inclusions designed to pamper you on your Assam vacation. If you wish to know more about Assam travel such as the best time to visit Assam, how to reach Assam, things to do in Assam and so on.\n" +
                        "\n" +
                        "Popular Assam Tour Packages in 2024\n" +
                        "1.Breathe at the cleanest Asian village Mawlynnong Package\t\n" +
                        "2.Alluring Meghalaya, Arunachal & Assam Package\n" +
                        "3.Magical Arunachal & Kaziranga Vacation Package\t\n" +
                        "4.Ziro Music Festival 2024 Group Tour Package\n" +
                        "5.Ziro Festival 2024 with Tezpur Package\t\n" +
                        "6.Wildlife - North East Special Package\n" +
                        "7.Second Innings North East Special Package\t\n" +
                        "8.Shillong and Guwahati 4N Package\t\n" +
                        "9.Ziro Festival 2024 with Tezpur Package\t\n" +
                        "10.Ziro Music Festival 2024 Group Tour Package",
                "22 april 2020",
                "$400-$800",
                "Popular Package",
                R.drawable.assam1,
                R.drawable.assam2,
                R.drawable.assam3,
                R.drawable.assam4,
                R.drawable.assam5,
                R.drawable.assam6,
                "Popular Package",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRycZIBorWOe6KHgYtHvw57U8uPXw8uzcoGSQfdPjWxQ_kCHvyL\n",
            )
        )
        //6
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQs6c0jFSJdeZl5Q4psIfn_2__BJ7Rpcpb7JTIh6fKBVdrPeFKs",
                "Gujarat",
                "Somnath Mandir",
                "4.5",
                "Are you looking for Gujarat packages from Surat? Look no further, because MakeMyTrip’s Gujarat holiday packages are the right choice for you. At MakeMyTrip you will find a wide range of customizable Gujarat tour packages to suit every kind of traveller from Surat . Choose from a collection of over 113 packages to Gujarat and enjoy unbeatable deals and discounts. MakeMyTrip offers you the convenience of online booking and payment and instant confirmations. MakeMyTrip packages to Gujarat from Surat are available both with and without flights, and start at prices as low as Rs 12723. Whether you are exploring Gujarat family packages, planning a Gujarat honeymoon, or just looking for a weekend getaway to Gujarat from Surat with your friends, you will find the right trip option here. With the carefully curated sightseeing and recommended activities, MakeMyTrip ensures that all essential Gujarat attractions are included in your itinerary. You can also pick from a wide range of additional tours and activities in and around Gujarat to customize your holiday package even further. MakeMyTrip packages from Surat to Gujarat are also conveniently categorized by theme, so that you can easily find your kind of holiday package. Be it an adventure trip, a relaxed holiday in Gujarat, a road-trip with friends, or a romantic getaway with your significant other, you will find the right Gujarat vacation at MakeMyTrip. Do also check out the specially curated range of luxury getaways from Surat to Gujarat, that come with MakeMyTrip’s assured services and the best of luxury hotels.",
                "18 april 2020",
                "$500-$800",
                "Popular Package",
                R.drawable.gujarat1,
                R.drawable.gujarat2,
                R.drawable.gujarat3,
                R.drawable.gujarat4,
                R.drawable.gujarat5,
                R.drawable.gujarat6,
                "Popular Package",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQs6c0jFSJdeZl5Q4psIfn_2__BJ7Rpcpb7JTIh6fKBVdrPeFKs",
            )
        )
        //7
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRz0s8eiDVHCBkF3ct10_vjm0sptG66u39XlQqAv0z0DMpe244t",
                "Punjab",
                "Himachal",
                "4.5",
                " If you are looking for a memorable Punjab trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Punjab tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 67 tour packages to Punjab with unbeatable deals and discounts. Explore the main Punjab sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Punjab Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Punjab vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Punjab Tour Packages offering the best of hotels and inclusions designed to pamper you on your Punjab vacation. If you wish to know more about Punjab travel such as the best time to visit Punjab, how to reach Punjab, things to do in Punjab and so on.\n" +
                        "\n" +
                        "Popular Punjab Tour Packages in 2024:\n" +
                        "1.Refreshing Himachal Trip Package\t\n" +
                        "2.Fabulous Himachal & Amritsar Trip - From Delhi Package\t\n" +
                        "3.Scenic Shimla & Manali with Golden Temple Darshan Package\t\n" +
                        "4.A Blissful Himachal Holiday from Delhi with Flights Package\t\n" +
                        "5.Spellbinding Himachal Vacay with Amritsar Package\t\n" +
                        "6.Fun Week in Himachal with Amritsar Package\t\n" +
                        "7.Fabulous Himachal & Amritsar Trip - From Delhi Package\t",
                "18 april 2020",
                "$500-$800",
                "Popular Package",
                R.drawable.punjab1,
                R.drawable.punjab2,
                R.drawable.punjab3,
                R.drawable.punjab4,
                R.drawable.punjab5,
                R.drawable.punjab6,
                "Popular Package",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRz0s8eiDVHCBkF3ct10_vjm0sptG66u39XlQqAv0z0DMpe244t",
            )
        )
        //8
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRK4EYrTO8z5SlMBVF2aNQ4MgysOIoj54AUtYhukaB7vLuxAYqo",
                "Rajasthan",
                "Udaipur",
                "4.5",
                "If you are looking for a memorable Rajasthan trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Rajasthan tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 290 tour packages to Rajasthan with unbeatable deals and discounts. Explore the main Rajasthan sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Rajasthan Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Rajasthan vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Rajasthan Tour Packages offering the best of hotels and inclusions designed to pamper you on your Rajasthan vacation. If you wish to know more about Rajasthan travel such as the best time to visit Rajasthan, how to reach Rajasthan, things to do in Rajasthan and so on.\n" +
                        "\n" +
                        "Popular Rajasthan Tour Packages in 2024\n" +
                        "\n" +
                        "1.Magical 3 Nights in Udaipur - Hero Package Package\t\n" +
                        "2.Most Wanted Rajasthan Package Package\t\n" +
                        "3.Udaipur, Kumbalgarh and Mount Abu Holiday - Land Only Package\t\n" +
                        "4.Unforgettable Jodhpur & Udaipur Holiday Package\t\n" +
                        "5.Soulmate Special Getaway to Rajasthan Packaget",
                "18 april 2020",
                "$500-$800",
                "Popular Package",
                R.drawable.rajshtan1,
                R.drawable.rajshtan2,
                R.drawable.rajshtan3,
                R.drawable.rajshtan4,
                R.drawable.rajshtan5,
                R.drawable.rajshtan6,
                "Popular Package",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRK4EYrTO8z5SlMBVF2aNQ4MgysOIoj54AUtYhukaB7vLuxAYqo",
            )
        )
        //9
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTazrSyYKJniHcQmos1WQ9-AsXUZZQY6ByVUsiZ-_V_7c7emqsY",
                "Jammu & Kashmir",
                "Gulmarg & Sonmarg",
                "3.5",
                "If you are looking for a memorable Kashmir trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Kashmir tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over tour packages to Kashmir with unbeatable deals and discounts. Explore the main Kashmir sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Kashmir Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Kashmir vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Kashmir Tour Packages offering the best of hotels and inclusions designed to pamper you on your Kashmir vacation. If you wish to know more about Kashmir travel such as the best time to visit Kashmir, how to reach Kashmir, things to do in Kashmir and so on.\n" +
                        "\n" +
                        "Popular Kashmir Packages:\n" +
                        "1.Mystical Kashmir Vacation - With Houseboat Stay Package\t\n" +
                        "2.Most Wanted Kashmir Package Package\t\n" +
                        "3.Amazing Kashmir Vacay with Gulmarg & Sonmarg Package\t\n" +
                        "4.Delightful Kashmir Holiday Package\t\n" +
                        "5.Magical Kashmir Holiday Package\t\n" +
                        "6.3N in Captivating Kashmir Package\t\n" +
                        "7.Joyful Kashmir Getaway with Gulmarg Package\t\n" +
                        "6 Days/5 Nights\t₹38,976\n" +
                        "9.Week-long Kashmir Delight with Doodhpathri Package\t\n" +
                        "10.Scenic Kashmir- Honeymoon Special Package",
                "18 april 2020",
                "$500-$800",
                "Popular Package",
                R.drawable.jammu1,
                R.drawable.jammu2,
                R.drawable.maldiv5,
                R.drawable.jammu4,
                R.drawable.jammu5,
                R.drawable.jammu6,
                "Popular Package",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTazrSyYKJniHcQmos1WQ9-AsXUZZQY6ByVUsiZ-_V_7c7emqsY",
            )
        )
        //10
        Popular_Package.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSWvtBQnDc7HizFORErLLd-DBtK06Fs5xsLI7PTLeWH-rCCt1sW",
                "Sikkim",
                "Gangtok & Darjeeling",
                "3.5",
                "If you are looking for a memorable Sikkim trip, MakeMyTrip holiday packages are the right choice for you. MakeMyTrip offers the widest range of customizable Sikkim tour to suit every kind of traveler from Delhi, Mumbai, Chennai or Bangalore. Explore from over 123 tour packages to Sikkim with unbeatable deals and discounts. Explore the main Sikkim sightseeing points with the variety of experiential tours and activities included in MakeMyTrip’s Sikkim Tour Packages.\n" +
                        "\n" +
                        "Be it a short trip or a long itinerary, a relaxing stay or an adventure holiday, whatever your travel preference, MakeMyTrip has the right Sikkim vacation tours for you to choose from. Looking to indulge? Don’t forget to check out our luxury Sikkim Tour Packages offering the best of hotels and inclusions designed to pamper you on your Sikkim vacation. If you wish to know more about Sikkim travel such as the best time to visit Sikkim, how to reach Sikkim, things to do in Sikkim and so on.\n" +
                        "\n" +
                        "Popular Sikkim Tour Packages in 2024:\n" +
                        "1.Leisurely North East with Kalimpong Package\t\n" +
                        "2.North East Grandeur - Gangtok and Darjeeling Package\t\n" +
                        "3.Sikkim Luxury Sojourn Package\t\n" +
                        "4.Gangtok, Pelling & Darjeeling Holiday Package\t\n" +
                        "5.Darjeeling & Gangtok Couple Special Package\t\n" +
                        "6.Cultural Darjeeling Holiday- MakeMyDay Special Package\t\n" +
                        "7.Scinitillating North East Package",
                "24 april 2024",
                "$500-$800",
                "Popular Package",
                R.drawable.sikkim1,
                R.drawable.sikkim2,
                R.drawable.sikkim3,
                R.drawable.sikkim4,
                R.drawable.sikkim6,
                R.drawable.jammu6,
                "Popular Package",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSWvtBQnDc7HizFORErLLd-DBtK06Fs5xsLI7PTLeWH-rCCt1sW",
            )
        )







        //clothing
        //1
        val individualPlace = ArrayList<RecyclerItem>()
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRJdJQSTQdqYmakfc3W-WXJoIuOwLCLRyO-wPgokwOsnSym_BAV",
                "Maldives",
                "Beaches",
                "4.5",
                "Home to sun-kissed beaches, swaying palms, sculpted coral reefs, luxury resorts and thrilling water sports, the tropical paradise called Maldives is a string of beautiful islands.",
                "18 april 2020",
                "$500-$800",
                "Individual Place",
                R.drawable.maldiv1,
                R.drawable.maldiv2,
                R.drawable.maldiv5,
                R.drawable.maldiv4,
                R.drawable.maldiv6,
                R.drawable.jammu5,
                "Individual Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRJdJQSTQdqYmakfc3W-WXJoIuOwLCLRyO-wPgokwOsnSym_BAV",
            )
        )
        //2
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTETkuQqSPlrG19IW2QPIt5HVyodIV4GULnMpTsgdRoeKMHLnBd",
                "Kasol",
                "hill-hamlet",
                "5.5",
                "The hill-hamlet of Kasol stands at over 1,580 meters above sea level, in Himachal Pradesh, and flaunts raw natural beauty with lush conifer forests, snow-capped Himalayan mountains and winding rivers.",
                "18 april 2020",
                "$900-$800",
                "Individual Place",
                R.drawable.kasol1,
                R.drawable.kasol2,
                R.drawable.kasol3,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Individual Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTETkuQqSPlrG19IW2QPIt5HVyodIV4GULnMpTsgdRoeKMHLnBd",
            )
        )
        //3
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTb4ArbXKN7ZpG2_m8KYd18gxasqmyG4R4pfT5NDJHWbeN-0Qdb",
                "Konark",
                "historic town",
                "4.5",
                "Konark is a historic town located in the Puri district of Odisha, India. It is famous for the magnificent Sun Temple, a UNESCO World Heritage Site and one of the most iconic landmarks in the country. Konark is a small town with a tranquil and laid-back atmosphere. It offers a break from the bustling cities and allows visitors to experience the rustic charm of rural Odisha.",
                "18 april 2020",
                "$200-$800",
                "Individual Place",
                R.drawable.konark1,
                R.drawable.konark2,
                R.drawable.konark3,
                R.drawable.konark4,
                R.drawable.konark5,
                R.drawable.konark6,
                "Individual Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTb4ArbXKN7ZpG2_m8KYd18gxasqmyG4R4pfT5NDJHWbeN-0Qdb",
            )
        )
        //4
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQqcZ4wzwfF_X-2vqGHaXlEDjqpX_5frtWuZXQxX6yy3kC656Pf",
                "Mathura",
                "birthplace",
                "4.5" ,
                "Famous as the birthplace of Lord Krishna and one among the 7 holy cities for Hindus, this city alongside the Yamuna river is an important pilgrimage site.",
                "18 april 2020",
                "$600-$800" ,
                "Individual Place",
                R.drawable.mathura1,
                R.drawable.mathura2,
                R.drawable.mathura3,
                R.drawable.mathura4,
                R.drawable.mathura5,
                R.drawable.mathura6,
                "Individual Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQqcZ4wzwfF_X-2vqGHaXlEDjqpX_5frtWuZXQxX6yy3kC656Pf",
            )
        )
        //5
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSbRJep9T4PW8g7QnZweviDA5zP0iuTsl7DoNO0i_8GYmL68-Cn",
                "Vrindavan",
                "historic town",
                "4.5",
                "Vrindavan, also spelled Vrindaban and Brindaban, is a historic town in Uttar Pradesh's Mathura district. Located in the Braj Bhoomi region, it is a religiously significant place, especially for Hindus as Lord Krishna, the Indian deity, spent most of his early life here. Vrindavan has approximately 5,500 temples devoted solely to Krishna and his supreme consort Radha.",
                "18 april 2020",
                "$800-$800",
                "Individual Place",
                R.drawable.mathura1,
                R.drawable.mathura2,
                R.drawable.mathura3,
                R.drawable.mathura4,
                R.drawable.mathura5,
                R.drawable.mathura6,
                "Individual Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSbRJep9T4PW8g7QnZweviDA5zP0iuTsl7DoNO0i_8GYmL68-Cn",
            )
        )
        //6
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSQqpscumleW5fSok8u8Jz49nPA6_yF85xO1n23Y4RKKTFN7ab_",
                "Allahabad",
                "Hindu pilgrimage",
                "4.2" ,
                "A Hindu pilgrimage centre located at the confluence of three holy rivers and the host of the grand Kumbh Mela, Prayagraj (formerly Allahabad) is believed to be the site where Lord Brahma, the creator of the universe, performed the first ever yajna.",
                "18 april 2020",
                "$400-$800",
                "Individual Place",
                R.drawable.pryagraj1,
                R.drawable.pryagraj2,
                R.drawable.pryagraj3,
                R.drawable.pryagraj4,
                R.drawable.mathura2,
                R.drawable.mathura6,
                "Individual Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSQqpscumleW5fSok8u8Jz49nPA6_yF85xO1n23Y4RKKTFN7ab_",
            )
        )
        //7
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRu0z5dJzPUPAVd-5f0KA9DgloeKKkWkL-Ca1XTLDHDfGt3rtLj",
                "Pushkar",
                "temples",
                "4.2" ,
                "Home to rare temples, shrines and picturesque locations, Pushkar in Rajasthan is where tourists throng to find their peace, and lose themselves in the rare beauty of Mother Nature.",
                "18 april 2020",
                "$400-$800",
                "Individual Place",
                R.drawable.pryagraj1,
                R.drawable.pryagraj2,
                R.drawable.pryagraj3,
                R.drawable.pryagraj4,
                R.drawable.mathura2,
                R.drawable.mathura6,
                "Individual Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRu0z5dJzPUPAVd-5f0KA9DgloeKKkWkL-Ca1XTLDHDfGt3rtLj",
            )
        )
        //8
        individualPlace.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtfsOrkzIn4-S_e2JRMmG-GUyuKgiEm-pU06APuy4VRXH2mBhV\n",
                "Amritsar",
                "Golden Temple",
                "4.2" ,
                "Home to rare temples, shrines and picturesque locations, Pushkar in Rajasthan is where tourists throng to find their peace, and lose themselves in the rare beauty of Mother Nature.",
                "18 april 2020",
                "$400-$800",
                "Individual Place",
                R.drawable.amritshar1,
                R.drawable.amritshar2,
                R.drawable.amrithshar3,
                R.drawable.mathura,
                R.drawable.vrindavan,
                R.drawable.mathura3,
                "Individual Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtfsOrkzIn4-S_e2JRMmG-GUyuKgiEm-pU06APuy4VRXH2mBhV\n",
            )
        )





        val Honeymoon = ArrayList<RecyclerItem>()
        //1
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQdlcuoWFRMIeSShk6d5KAv_bGogijWaFtXIIbv7lvm5tHzRYka",
                "Havelock Island",
                "Swaraj Island",
                "4.2" ,
                "Also known as Swaraj Island, Havelock Island is a part of Ritchie’s Archipelago in the Andamans. Its dazzling white sand beaches and fascinating coral reefs are admired by nature lovers from around the world.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.punjab1,
                R.drawable.jammu6,
                R.drawable.amrithshar3,
                R.drawable.havelook1,
                R.drawable.havelook,
                R.drawable.goa,
                "Honeymoon Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQdlcuoWFRMIeSShk6d5KAv_bGogijWaFtXIIbv7lvm5tHzRYka",
            )
        )
        //2
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRu7sNJyYJI_KWKfowF03G96Ya4OH1PcNgrMvi8J0M5HOUGiS9g\n",
                "Havelock Island",
                "Swaraj Island",
                "4.2" ,
                "Popularly known as India’s party capital, Goa seduces travellers from all around the globe with its boho beaches, ancient churches, majestic forts and unbeatable nightlife!",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.goa1,
                R.drawable.goa2,
                R.drawable.goa3,
                R.drawable.goa6,
                R.drawable.goa5,
                R.drawable.goa4,
                "Honeymoon Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRu7sNJyYJI_KWKfowF03G96Ya4OH1PcNgrMvi8J0M5HOUGiS9g\n",
            )
        )
        //3
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUw3dvnPZWIX6WqWDyw8J8MAvEj_Zu6je2cH1KQqvdGmyACsqw",
                "Manali",
                "River Beas",
                "4.2" ,
                "Nestled on the banks of River Beas, Manali will take your breath away with its snow-kissed peaks, lush valleys and heart-pumping adventure experiences in the heart of Himalayas",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.punjab5,
                R.drawable.jammu,
                R.drawable.sikkim,
                R.drawable.sikkim3,
                R.drawable.assam3,
                R.drawable.assam4,
                "Honeymoon Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUw3dvnPZWIX6WqWDyw8J8MAvEj_Zu6je2cH1KQqvdGmyACsqw",
            )
        )
        //4
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTT0uhyvPxnecljOiGNzAEyGD1_5SBN7ZmEUqDi_AEiE0T1HNFt",
                "Srinagar",
                "Jahangir",
                "4.2" ,
                "Jahangir’s ‘heaven on earth’, Srinagar offers the picturesque Dal Lake, breath-taking mountain views, stunning Mughal gardens and sprawling orchards.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.jammu1,
                R.drawable.jammu2,
                R.drawable.jammu4,
                R.drawable.jammu6,
                R.drawable.jammu5,
                R.drawable.assam4,
                "Individual Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTT0uhyvPxnecljOiGNzAEyGD1_5SBN7ZmEUqDi_AEiE0T1HNFt",
            )
        )
        //5
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSz-oJBiVDq8bmO_xqDjUdwj1FVq-zTPia5RFM2wpoiLkpJMvWq",
                "Kerala",
                "Munnar",
                "4.2" ,
                "The biggest tea-planation area in South India, Munnar is also home to several endangered species. It is a spectacular stretch of silent mountains and swaying swathes of thick forests.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.kasol,
                R.drawable.kasol2,
                R.drawable.kasol3,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Honeymoon Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSz-oJBiVDq8bmO_xqDjUdwj1FVq-zTPia5RFM2wpoiLkpJMvWq",
            )
        )
        //6
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSDGNd-Vc_LcpIEX7ZqxlftN8DiP_3LW9KTiQ3qXetOwMemj7UO\n",
                "Kerala",
                "Alappuzha",
                "4.2" ,
                "Also known as Alappuzha, Aleppey will take your breath away with its emerald green backwaters, palm-fringed lakes and beautiful stretches of lush paddy fields in the heart of Kerala.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.punjab1,
                R.drawable.punjab3,
                R.drawable.konark,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Honeymoon Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSDGNd-Vc_LcpIEX7ZqxlftN8DiP_3LW9KTiQ3qXetOwMemj7UO\n",
            )
        )
        //7
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS-BepDwnowxtOak2R344VwuAJM0clK5nQPCz3XROamVSpfLwli",
                "Sikkim",
                "Gangtok",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.sikkim1,
                R.drawable.sikkim2,
                R.drawable.sikkim3,
                R.drawable.sikkim4,
                R.drawable.goa,
                R.drawable.punjab5,
                "Individual Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS-BepDwnowxtOak2R344VwuAJM0clK5nQPCz3XROamVSpfLwli",
            )
        )
        //8
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTiECM4Dsrc5g8sCsGOEZn2hxq1ICinf7uG-L7m7xZSP-1vBAKg\n",
                "Coorag",
                "lap of nature",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Honeymoon Place",
                R.drawable.manali,
                R.drawable.punjab1,
                R.drawable.punjab2,
                R.drawable.punjab3,
                R.drawable.punjab4,
                R.drawable.punjab5,
                "Individual Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTiECM4Dsrc5g8sCsGOEZn2hxq1ICinf7uG-L7m7xZSP-1vBAKg\n",
            )
        )





        val Romantic = ArrayList<RecyclerItem>()
        //1
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtMnxOFwWGI85xTfWPGN3ze8AV4o24mR6gKZsdxxzB9sq4LwAS",
                "Bali",
                "beaches",
                "4.2" ,
                "Also known as Swaraj Island, Havelock Island is a part of Ritchie’s Archipelago in the Andamans. Its dazzling white sand beaches and fascinating coral reefs are admired by nature lovers from around the world.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.punjab1,
                R.drawable.jammu6,
                R.drawable.amrithshar3,
                R.drawable.havelook1,
                R.drawable.havelook,
                R.drawable.goa,
                "Romantic Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtMnxOFwWGI85xTfWPGN3ze8AV4o24mR6gKZsdxxzB9sq4LwAS",
            )
        )
        //2
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQIuPXcSVz3i4QD5SjRJOnT9yZtgR8wc2RbL6wkG5o9egvBdE-j",
                "Coorag",
                "Hill Station",
                "4.2" ,
                "Popularly known as India’s party capital, Goa seduces travellers from all around the globe with its boho beaches, ancient churches, majestic forts and unbeatable nightlife!",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.goa1,
                R.drawable.goa2,
                R.drawable.goa3,
                R.drawable.goa6,
                R.drawable.goa5,
                R.drawable.goa4,
                "Romantic Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQIuPXcSVz3i4QD5SjRJOnT9yZtgR8wc2RbL6wkG5o9egvBdE-j",
            )
        )
        //3
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS8rN2xu06zULZwqbg98A0QUDPtW6l4deLdtSIkiIghcdltE7Fu",
                "Bentota",
                "Beach",
                "4.2" ,
                "Nestled on the banks of River Beas, Manali will take your breath away with its snow-kissed peaks, lush valleys and heart-pumping adventure experiences in the heart of Himalayas",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.punjab5,
                R.drawable.jammu,
                R.drawable.sikkim,
                R.drawable.sikkim3,
                R.drawable.assam3,
                R.drawable.assam4,
                "Romantic Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS8rN2xu06zULZwqbg98A0QUDPtW6l4deLdtSIkiIghcdltE7Fu",
            )
        )
        //4
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRCDFtYJnQ5qO6feHXYc1WpHEl3rSA6A_zgKlmWPEd86hDe8-YE",
                "Gulmarg",
                "White Hill",
                "4.2" ,
                "Jahangir’s ‘heaven on earth’, Srinagar offers the picturesque Dal Lake, breath-taking mountain views, stunning Mughal gardens and sprawling orchards.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.jammu1,
                R.drawable.jammu2,
                R.drawable.jammu4,
                R.drawable.jammu6,
                R.drawable.jammu5,
                R.drawable.assam4,
                "Romantic Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRCDFtYJnQ5qO6feHXYc1WpHEl3rSA6A_zgKlmWPEd86hDe8-YE",
            )
        )
        //5
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQGx4GzADtUCfKsXN_-FBqDHZNVxdnJsXJdsqKhLqtVh3ZA22AI",
                "Havelook",
                "Island",
                "4.2" ,
                "The biggest tea-planation area in South India, Munnar is also home to several endangered species. It is a spectacular stretch of silent mountains and swaying swathes of thick forests.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.kasol,
                R.drawable.kasol2,
                R.drawable.kasol3,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Romantic Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQGx4GzADtUCfKsXN_-FBqDHZNVxdnJsXJdsqKhLqtVh3ZA22AI",
            )
        )
        //6
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ69q1pw5l-6jk56horoUsrO4VSE5HJRzaJufR3fQk0F_dkGgd",
                "Lakshadweep",
                "Island",
                "4.2" ,
                "Also known as Alappuzha, Aleppey will take your breath away with its emerald green backwaters, palm-fringed lakes and beautiful stretches of lush paddy fields in the heart of Kerala.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.punjab1,
                R.drawable.punjab3,
                R.drawable.konark,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Romantic Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ69q1pw5l-6jk56horoUsrO4VSE5HJRzaJufR3fQk0F_dkGgd",
            )
        )
        //7
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRr31HZ2nFhOCljZPM71YRtVAWI6RFJSK7yDmDXrYfHSeONRdeq",
                "Langkawi",
                "Beach",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.sikkim1,
                R.drawable.sikkim2,
                R.drawable.sikkim3,
                R.drawable.sikkim4,
                R.drawable.goa,
                R.drawable.punjab5,
                "Romantic Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRr31HZ2nFhOCljZPM71YRtVAWI6RFJSK7yDmDXrYfHSeONRdeq",
            )
        )
        //8
        Romantic.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNW7RvgXg2hWVRUj-K0_Rgmi_Bmk1yR8sPMFfB-rY8-HDETb7r",
                "Maldiv",
                "Couple Beack",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.manali,
                R.drawable.punjab1,
                R.drawable.punjab2,
                R.drawable.punjab3,
                R.drawable.punjab4,
                R.drawable.punjab5,
                "Romantic Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNW7RvgXg2hWVRUj-K0_Rgmi_Bmk1yR8sPMFfB-rY8-HDETb7r",
            )
        )
        //9
        Honeymoon.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQmESi5Sjh_7XyQElsv5ygogXwZFW5Q7HKaA_TLNSKeVLPLzaDP",
                "Manali",
                "lap of nature",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Romantic Place",
                R.drawable.manali,
                R.drawable.punjab1,
                R.drawable.punjab2,
                R.drawable.punjab3,
                R.drawable.punjab4,
                R.drawable.punjab5,
                "Romatic Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQmESi5Sjh_7XyQElsv5ygogXwZFW5Q7HKaA_TLNSKeVLPLzaDP",
            )
        )



        val Foddie = ArrayList<RecyclerItem>()
        //1
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQ7JkoqfdJuD7ds08wmV50lCA9m2B7vms2SNBhikEoFuGRBjF42\n",
                "Punjab",
                "Amritsar",
                "4.2" ,
                "Also known as Swaraj Island, Havelock Island is a part of Ritchie’s Archipelago in the Andamans. Its dazzling white sand beaches and fascinating coral reefs are admired by nature lovers from around the world.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.punjab1,
                R.drawable.jammu6,
                R.drawable.amrithshar3,
                R.drawable.havelook1,
                R.drawable.havelook,
                R.drawable.goa,
                "Foddie Dream Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQ7JkoqfdJuD7ds08wmV50lCA9m2B7vms2SNBhikEoFuGRBjF42\n",
            )
        )
        //2
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRM_waYi6BMFCCNjjAv7qly6McELIXIaWWHKwOtf2gNA6xgWokI\n",
                "Utter Pradesh",
                "Agra",
                "4.2" ,
                "Popularly known as India’s party capital, Goa seduces travellers from all around the globe with its boho beaches, ancient churches, majestic forts and unbeatable nightlife!",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.goa1,
                R.drawable.goa2,
                R.drawable.goa3,
                R.drawable.goa6,
                R.drawable.goa5,
                R.drawable.goa4,
                "Foddie Dream Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRM_waYi6BMFCCNjjAv7qly6McELIXIaWWHKwOtf2gNA6xgWokI\n",
            )
        )
        //3
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSLHRWkEVFezrHSQF8QetvkmruUNFjMl571jw84_V_vZsPtpHMB\n",
                "Thiland",
                "Bangkok",
                "4.2" ,
                "Nestled on the banks of River Beas, Manali will take your breath away with its snow-kissed peaks, lush valleys and heart-pumping adventure experiences in the heart of Himalayas",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.punjab5,
                R.drawable.jammu,
                R.drawable.sikkim,
                R.drawable.sikkim3,
                R.drawable.assam3,
                R.drawable.assam4,
                "Foddie Dream Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSLHRWkEVFezrHSQF8QetvkmruUNFjMl571jw84_V_vZsPtpHMB\n",
            )
        )
        //4
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTs0tHMuS0eaiUtGm5lNEf1rHeYLeIK75pR9AkUmN-oVQwILfHR\n",
                "Bengal",
                "Kolkata",
                "4.2" ,
                "Jahangir’s ‘heaven on earth’, Srinagar offers the picturesque Dal Lake, breath-taking mountain views, stunning Mughal gardens and sprawling orchards.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.jammu1,
                R.drawable.jammu2,
                R.drawable.jammu4,
                R.drawable.jammu6,
                R.drawable.jammu5,
                R.drawable.assam4,
                "Foddie Dream Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTs0tHMuS0eaiUtGm5lNEf1rHeYLeIK75pR9AkUmN-oVQwILfHR\n",
            )
        )
        //5
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRaUGpLASOjLdTMHnlUrWA8MTFMy8XjIze18jgnjlZooeIagNaV",
                "City of pearls",
                "Hyderbad",
                "4.2" ,
                "The biggest tea-planation area in South India, Munnar is also home to several endangered species. It is a spectacular stretch of silent mountains and swaying swathes of thick forests.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.kasol,
                R.drawable.kasol2,
                R.drawable.kasol3,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Foddie Dream Place",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRaUGpLASOjLdTMHnlUrWA8MTFMy8XjIze18jgnjlZooeIagNaV",
            )
        )
        //6
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbIuL3dEQ7_qYZtiSfzuuTuZN0VvKefwoE0y7tK5zDw0cGcbHG\n",
                "Delhi",
                "Captial of City",
                "4.2" ,
                "Also known as Alappuzha, Aleppey will take your breath away with its emerald green backwaters, palm-fringed lakes and beautiful stretches of lush paddy fields in the heart of Kerala.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.punjab1,
                R.drawable.punjab3,
                R.drawable.konark,
                R.drawable.kasol4,
                R.drawable.kasol5,
                R.drawable.kasol6,
                "Foddie Dream Place",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbIuL3dEQ7_qYZtiSfzuuTuZN0VvKefwoE0y7tK5zDw0cGcbHG\n",
            )
        )
        //7
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTvFPbY9ZTd6jZO8Huc9OCbiqPwL5wNiRlvF6YvxT-Ge_MC5y91\n",
                "Vietname",
                "Hanoi",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.sikkim1,
                R.drawable.sikkim2,
                R.drawable.sikkim3,
                R.drawable.sikkim4,
                R.drawable.goa,
                R.drawable.punjab5,
                "Foddie Dream Place",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTvFPbY9ZTd6jZO8Huc9OCbiqPwL5wNiRlvF6YvxT-Ge_MC5y91\n",
            )
        )
        //8
        Foddie.add(
            RecyclerItem(
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRdm43JGp_kUZcc1xFP0ZeQ6JBjl7jg1tWor4IvICr2zrIImuGq\n",
                "Thiland",
                "Pattaya",
                "4.2" ,
                "Dotted with sacred Buddhist monasteries, the largest town in Sikkim, Gangtok, impresses with crystalline glacial lakes and dense forests and stunning views of the Himalayan mountains.",
                "18 april 2020",
                "$400-$800",
                "Foddie Dream Place",
                R.drawable.manali,
                R.drawable.punjab1,
                R.drawable.punjab2,
                R.drawable.punjab3,
                R.drawable.punjab4,
                R.drawable.punjab5,
                "Foddie Dream Place",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRdm43JGp_kUZcc1xFP0ZeQ6JBjl7jg1tWor4IvICr2zrIImuGq\n",
            )
        )

        // mList.add(0, DataItem(DataItemType.latest_trip, latest_trip))
        // mList = ArrayList()
        //val latestTrip = ArrayList<RecyclerItem>()
        mList.clear()
        mList.add(0,DataItem(DataItemType.Popular_Package, Popular_Package))
        mList.add(1,DataItem(DataItemType.banner, Banner(R.drawable.banner1)))
        mList.add(2,DataItem(DataItemType.Individual_place, individualPlace))
        mList.add(3,DataItem(DataItemType.Individual_place, Romantic.asReversed()))
        mList.add(4,DataItem(DataItemType.banner, Banner(R.drawable.banner2)))
        mList.add(5,DataItem(DataItemType.Individual_place, Honeymoon))
        mList.add(6,DataItem(DataItemType.Popular_Package, Popular_Package.asReversed()))
        mList.add(7,DataItem(DataItemType.Individual_place, Romantic))
        mList.add(8,DataItem(DataItemType.Individual_place, Foddie))
        mList.add(9,DataItem(DataItemType.banner, Banner(R.drawable.banner3)))

        adapter.notifyDataSetChanged()
    }


}