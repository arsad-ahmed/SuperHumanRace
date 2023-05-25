package com.example.superhuman.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(link: String)
{
    Picasso.get().load(link).into(this)
}