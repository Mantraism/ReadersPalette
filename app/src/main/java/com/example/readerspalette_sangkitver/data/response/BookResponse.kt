package com.example.readerspalette_sangkitver.data.response

import com.google.gson.annotations.SerializedName

data class BookResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("year")
	val year: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("publisher")
	val publisher: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String
)
