package com.example.readerspalette_sangkitver.ui.home

import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readerspalette_sangkitver.data.response.BookResponse
import com.example.readerspalette_sangkitver.data.response.DataItem
import com.example.readerspalette_sangkitver.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _book = MutableLiveData<DataItem>()
    val book: LiveData<DataItem> = _book

    private val _listBook = MutableLiveData<List<DataItem>>()
    val listBook : LiveData<List<DataItem>> = _listBook

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var dataItem = arrayListOf<DataItem>()

    companion object {
        private const val TAG = "MainViewModel"
        private const val BOOK_KEYWORDS = ""
    }

    init {
        findBook("")
    }
    fun findBook(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getBooks(query)
        client.enqueue(object : Callback<BookResponse>{
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                _isLoading.value=false
                if (response.isSuccessful){
                    _listBook.value = response.body()?.data
                } else {
                    Log.e(TAG,"onResponse Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure: ${t.message}")
            }
        })
    }
}