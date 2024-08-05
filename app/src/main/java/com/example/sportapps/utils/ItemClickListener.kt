package com.example.sportapps.utils;

import com.example.sportapps.response.Data


interface ItemClickListener {
    fun onClick(eventmodel: Data,position:Int)
}