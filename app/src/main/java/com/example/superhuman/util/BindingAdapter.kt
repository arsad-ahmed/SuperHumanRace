package com.example.superhuman.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.superhuman.R

@BindingAdapter("imageUrl")
fun loadImage(imageView:ImageView, link: String)
{

    if(link.isNotEmpty())
    {
        imageView.loadImage(link)
    }
}

@BindingAdapter("countText")
fun setCount(textView:TextView,count:Int)
{
    textView.text="($count)"
}

@BindingAdapter("loadIcon")
fun loadIcons(imageView:ImageView,iconType:String)
{
    if(iconType=="wallet")
    {
        imageView.setImageResource(R.drawable.donation)
    }
    else if(iconType=="question")
    {
        imageView.setImageResource(R.drawable.question)
    }
    else if(iconType=="poll")
    {
        imageView.setImageResource(R.drawable.polling)
    }
    else if(iconType=="puzzle")
    {
        imageView.setImageResource(R.drawable.puzzle)
    }
}