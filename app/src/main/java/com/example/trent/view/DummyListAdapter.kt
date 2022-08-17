package com.example.trent.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trent.R
import com.example.trent.model.ProductsItem
import com.example.trent.util.OnItemClickListener
import com.example.trent.util.getProgressDrawable
import com.example.trent.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class DummyListAdapter(var productsItem: ArrayList<ProductsItem>, private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<DummyListAdapter.DummyViewHolder>() {


    fun updateDummy(newDummy: List<ProductsItem>) {
        productsItem.clear()
        productsItem.addAll(newDummy)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) : DummyViewHolder{

      return DummyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount() = productsItem.size

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.bind(productsItem[position])

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClickListener(position)
        }
        }

   class DummyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       private val imageView = itemView.imageView
       private val itemName = itemView.name
       private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(productsItem: ProductsItem) {
            itemName.text = productsItem.title
            imageView.loadImage(productsItem.thumbnail, progressDrawable)

        }

    }
}