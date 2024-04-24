package com.example.readerspalette_sangkitver.data.retrofit

import com.example.readerspalette_sangkitver.data.response.BookDetailResponse
import com.example.readerspalette_sangkitver.data.response.BookResponse
import com.example.readerspalette_sangkitver.data.response.CommentResponse
import com.example.readerspalette_sangkitver.data.response.Data
import com.example.readerspalette_sangkitver.data.response.DataItem
import com.example.readerspalette_sangkitver.data.response.PostCommentResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("books/search/data")
    fun getBooks(
        @Query("keywords") query: String
    ): Call<BookResponse>

    @GET("books/{id}")
    fun getBookDetail(
        @Path("id") id : Int
    ): Call<BookDetailResponse>

    @GET("comment/{id}")
    fun getComment(
        @Path("id") id : Int
    ): Call<CommentResponse>

    @FormUrlEncoded
    @POST("comment/{id}")
    fun postComment(
        @Path("id") id : Int,
        @Field("user_id") user_id : Int,
        @Field("comment") comment : String
    ) : Call<PostCommentResponse>
}