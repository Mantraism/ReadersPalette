package com.example.readerspalette_sangkitver.data.response

import com.google.gson.annotations.SerializedName

data class PostCommentResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class PostCommentDataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("is_deleted")
	val isDeleted: Boolean,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("book_id")
	val bookId: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
