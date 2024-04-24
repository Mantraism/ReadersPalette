package com.example.readerspalette_sangkitver.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.readerspalette_sangkitver.data.response.DataItem
import com.example.readerspalette_sangkitver.databinding.ItemBookBinding
import com.example.readerspalette_sangkitver.ui.detail.DetailBookActivity

class BookAdapter : ListAdapter<DataItem, BookAdapter.MyViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
    class MyViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book : DataItem){
            binding.itemTitle.text = "${book.title}"
            binding.itemAuthor.text = "${book.author}"
            Glide.with(itemView)
                .load(book.photo)
                .centerCrop()
                .into(binding.itemPhoto)

            itemView.setOnClickListener {
                Log.d("itemClick","Test")
                val intent = Intent(itemView.context, DetailBookActivity::class.java)
                intent.putExtra(DetailBookActivity.EXTRA_ID,book.id)
                itemView.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem ==newItem
            }
        }
    }
}