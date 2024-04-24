package com.example.readerspalette_sangkitver.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.readerspalette_sangkitver.data.response.CommentDataItem
import com.example.readerspalette_sangkitver.databinding.ItemCommentBinding


class CommentAdapter : ListAdapter<CommentDataItem, CommentAdapter.CommentViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position : Int){
        val comment = getItem(position)
        holder.bind(comment)
    }
    class CommentViewHolder(val binding: ItemCommentBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment : CommentDataItem){
            binding.tvComment.text =  "${comment.comment}\n- ${comment.username}"
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommentDataItem>(){
            override fun areItemsTheSame(
                oldItem: CommentDataItem,
                newItem: CommentDataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CommentDataItem,
                newItem: CommentDataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}