package com.example.gramoday.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gramoday.data.models.Product
import com.example.gramoday.databinding.LayoutProductsItemBinding

class ProductAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Product, ProductAdapter.MyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutProductsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.cmdtyID == newItem.cmdtyID
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(private val binding: LayoutProductsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.tvMandiRates.text = product.cmdtyStdName + " Mandi Rates"
            val address = product.posts[0]
            binding.tvAddress.text =
                address.marketStdName + address.loclevel3Name + address.loclevel2ShortName
            val minPrice = address.priceDetails[0].minPrice.toString()
            val maxPrice = address.priceDetails[0].maxPrice.toString()
            binding.tvPrice.text = "₹ $minPrice - $maxPrice / 1 kg"

            val date = address.updatedAt.substring(0, 10)
            binding.tvDate.text = date

        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

}