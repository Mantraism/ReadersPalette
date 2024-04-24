package com.example.readerspalette_sangkitver.data.response

import com.google.gson.annotations.SerializedName

data class BookDetailResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("year")
	val year: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("genre")
	val genre: Any,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("publisher")
	val publisher: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("synopsis")
	val synopsis: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
