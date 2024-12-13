package com.example.myapplication.models

class ImageItem(val id: String, val url: String) {
    // You can override the toString method if needed
    override fun toString(): String {
        return "ImageItem(id='$id', url='$url')"
    }
}

