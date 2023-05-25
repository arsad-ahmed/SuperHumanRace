package com.example.superhuman.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.superhuman.databinding.ItemRvBinding
import com.example.superhuman.model.DataX
import com.example.superhuman.model.HealthModel

class ListItemAdapter():RecyclerView.Adapter<ListItemAdapter.MainItemViewHolder>()
{
    private val healthModel = mutableListOf<DataX>()

    fun addProducts(list: List<DataX>)
    {
        healthModel.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):MainItemViewHolder
    {
        return MainItemViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount():Int
    {
        return healthModel.size
    }

    override fun onBindViewHolder(holder:MainItemViewHolder, position:Int)
    {
        holder.binding.items=healthModel[position]
    }

    inner class MainItemViewHolder(val binding:ItemRvBinding) :RecyclerView.ViewHolder(binding.root)
}