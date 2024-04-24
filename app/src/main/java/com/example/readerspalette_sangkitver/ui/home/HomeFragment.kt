package com.example.readerspalette_sangkitver.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.readerspalette_sangkitver.R
import com.example.readerspalette_sangkitver.data.response.BookResponse
import com.example.readerspalette_sangkitver.data.response.DataItem
import com.example.readerspalette_sangkitver.data.retrofit.ApiConfig
import com.example.readerspalette_sangkitver.databinding.FragmentHomeBinding
import com.example.readerspalette_sangkitver.ui.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object{
        private const val TAG = "HomeFragment"
        private const val BOOK_KEYWORDS = ""

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val homeViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvListBook.layoutManager = layoutManager

        homeViewModel.listBook.observe(viewLifecycleOwner) {data ->
            setBookData(data)
        }
        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        with(binding){
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(!query.isNullOrEmpty()){
                        searchView.clearFocus()
                        homeViewModel.findBook(query)
                    }
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }

        return root
    }
//    private fun findBook(){
//        showLoading(true)
//        val client = ApiConfig.getApiService().getBooks(BOOK_KEYWORDS)
//        client.enqueue(object : Callback<BookResponse>{
//            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
//                showLoading(false)
//                if(response.isSuccessful){
//                    val responseBody = response.body()
//                    if (responseBody != null){
//                        setBookData(responseBody.data)
//                    }
//                } else {
//                    Log.e(TAG,"onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setBookData(data : List<DataItem>) {
        val adapter = BookAdapter()
        adapter.submitList(data)
        binding.rvListBook.adapter = adapter
    }


    private fun showLoading(isLoading : Boolean) {
        if (isLoading){
            binding.progrerssBarMain.visibility = View.VISIBLE
        } else {
            binding.progrerssBarMain.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}