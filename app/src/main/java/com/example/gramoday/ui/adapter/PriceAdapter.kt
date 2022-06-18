package com.example.gramoday.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gramoday.data.models.PriceDetail
import com.example.gramoday.databinding.LayoutPriceItemBinding

class PriceAdapter :
    ListAdapter<PriceDetail, PriceAdapter.MyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutPriceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PriceDetail>() {
        override fun areItemsTheSame(oldItem: PriceDetail, newItem: PriceDetail): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: PriceDetail,
            newItem: PriceDetail
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(private val binding: LayoutPriceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(price: PriceDetail) {
            binding.tvShop.text = price.varietyName
            if (price.gradeName != null) {
                binding.tvShop.text = binding.tvShop.text.toString() + price.gradeName
            }
            binding.tvMinPrice.text = price.minPrice.toString()
            binding.tvMaxPrice.text = price.maxPrice.toString()
        }
    }


}