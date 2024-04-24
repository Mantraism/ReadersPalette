package com.example.readerspalette_sangkitver.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readerspalette_sangkitver.data.response.BookDetailResponse
import com.example.readerspalette_sangkitver.data.response.CommentDataItem
import com.example.readerspalette_sangkitver.data.response.CommentResponse
import com.example.readerspalette_sangkitver.data.response.DataItem
import com.example.readerspalette_sangkitver.data.response.Data
import com.example.readerspalette_sangkitver.data.response.PostCommentResponse
import com.example.readerspalette_sangkitver.data.retrofit.ApiConfig
import com.example.readerspalette_sangkitver.ui.CommentAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _book = MutableLiveData<Data>()
    val book: LiveData<Data> = _book

    private val _listBook = MutableLiveData<List<DataItem>>()
    val listBook : LiveData<List<DataItem>> = _listBook

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var dataItem = arrayListOf<DataItem>()

    private val _commentList = MutableLiveData<List<CommentDataItem>>()
    val commentList: LiveData<List<CommentDataItem>> = _commentList

    private val _postCommentResult = MutableLiveData<PostCommentResponse>()
    val postCommentResult: LiveData<PostCommentResponse> = _postCommentResult

    fun getDetailById(query: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getBookDetail(query)
        client.enqueue(object : Callback<BookDetailResponse> {
            override fun onResponse(call: Call<BookDetailResponse>, response: Response<BookDetailResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val bookDetailResponse = response.body()
                    if (bookDetailResponse != null && bookDetailResponse.status == "berhasil") {
                        val bookDetail = bookDetailResponse.data
                        _book.value = bookDetail
                        Log.e(TAG, "onSuccess: $bookDetail")
                    } else {
                        Log.e(TAG, "onFailure: Invalid response or status is not 'berhasil'")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}", t)
            }
        })
    }

    fun getComments(bookId: Int) {
        val client = ApiConfig.getApiService().getComment(bookId)
        client.enqueue(object : Callback<CommentResponse> {
            override fun onResponse(
                call: Call<CommentResponse>,
                response: Response<CommentResponse>
            ) {
                if (response.isSuccessful) {
                    _commentList.value = response.body()?.data
                    Log.e(TAG, "Comment Recieved: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                Log.e(TAG, "Error fetching comments: ${t.message}")
            }
        })
    }

    fun postComment(bookId: Int, userId: Int, comment: String) {
        val client = ApiConfig.getApiService().postComment(bookId, 2, comment)
        client.enqueue(object : Callback<PostCommentResponse> {
            override fun onResponse(
                call: Call<PostCommentResponse>,
                response: Response<PostCommentResponse>
            ) {
                if (response.isSuccessful) {
                    // Handle successful response
                    Log.d(TAG, "Post comment successful")
                    // You can perform any additional actions here if needed
                } else {
                    // Handle unsuccessful response
                    Log.e(TAG, "Post comment failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostCommentResponse>, t: Throwable) {
                // Handle failure
                Log.e(TAG, "Failed to post comment: ${t.message}")
            }
        })
    }
    companion object {
        private const val TAG = "DetailViewModel"
    }
}