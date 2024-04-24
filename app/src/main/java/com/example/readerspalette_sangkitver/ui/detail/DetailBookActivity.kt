package com.example.readerspalette_sangkitver.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels


import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.readerspalette_sangkitver.R
import com.example.readerspalette_sangkitver.data.response.CommentDataItem
import com.example.readerspalette_sangkitver.data.retrofit.ApiConfig
import com.example.readerspalette_sangkitver.databinding.ActivityDetailBookBinding
import com.example.readerspalette_sangkitver.ui.CommentAdapter

class DetailBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBookBinding
    private lateinit var commentAdapter: CommentAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvComment.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter()
        binding.rvComment.adapter = commentAdapter


        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            detailViewModel.getDetailById(id)
            detailViewModel.book.observe(this) { book ->
                if (book?.photo != null) {
                    Glide.with(this)
                        .load(book.photo)
                        .into(binding.ivPicture)
                } else {
                    binding.ivPicture.setImageResource(R.drawable.mantra)
                }

                binding.tvTitle.text = book?.title ?: "Title Unknown"
                binding.tvAuthor.text = book?.author ?: "Author Unknown"
                binding.tvDescription.text = book?.synopsis ?: "No Description Available"
                binding.tvGenre.text = book?.genre?.toString() ?: "Genre Not Specified"
                binding.tvPublisher.text = book?.publisher.toString() ?: "Publisher not specified"
                binding.tvYear.text = book?.year?.toString() ?: "Year Not Specified"
            }
        } else {
            Toast.makeText(this, "Invalid book ID", Toast.LENGTH_LONG).show()
            finish()
        }
        binding.btnSend.setOnClickListener {
            val bookId = intent.getIntExtra(EXTRA_ID, 0)
            val userId = 1
            val comment = binding.edReview.text.toString()

            if (comment.isNotEmpty()) {
                detailViewModel.postComment(bookId, userId, comment)
                binding.edReview.text?.clear()
                Toast.makeText(this, "Komentar Ditambahkan", Toast.LENGTH_LONG).show()
            }

        }

        // Observe the commentList LiveData
        detailViewModel.commentList.observe(this, { commentList ->
            // Update the adapter with the new comment list
            commentAdapter.submitList(commentList)
        })

        // Fetch comments when the activity starts
        detailViewModel.getComments(id)
    }



//        if (intent.getStringExtra(EXTRA_ID) != null){
//            val id = intent.getStringExtra(EXTRA_ID)
//            detailViewModel.getDetailById(id.toInt())
//            detailViewModel.book.observe(this) { book->
//                Glide.with(this)
//                    .load(book.photo)
//                    .into(binding.ivPicture)
//                binding.tvTitle.text = book.title
//                binding.tvAuthor.text = book.author
//                binding.tvDescription.text = book.synopsis
//                binding.tvGenre.text = book.genre.toString()
//                binding.tvYear.text = book.year
//            }
//        }


    companion object {
        const val EXTRA_ID = "book.id"
    }
}

