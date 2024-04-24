package com.example.readerspalette_sangkitver.data.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("data")
	val data: List<CommentDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class CommentDataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("book_id")
	val bookId: Int,

	@field:SerializedName("username")
	val username: String
)
