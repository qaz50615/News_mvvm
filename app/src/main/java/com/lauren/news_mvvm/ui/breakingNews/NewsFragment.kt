package com.lauren.news_mvvm.ui.breakingNews

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.lauren.news_mvvm.News
import com.lauren.news_mvvm.NewsAdapter
import com.lauren.news_mvvm.NewsState
import com.lauren.news_mvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search_list.*
import kotlinx.coroutines.*

class NewsFragment: Fragment() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        newsViewModel.fetchData()

    }

    private fun initView() {

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        adapter = NewsAdapter(requireContext())
        recyclerView.adapter = adapter
        newsViewModel.newsState.value = NewsState.Loading

        newsViewModel.newsState.observe(this.viewLifecycleOwner, Observer {
            observerData(it)
        })

        searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsViewModel.filterData(newText?:"")
                return true
            }

        })

    }

    private fun observerData(newsState: NewsState) {
        when (newsState) {
            is NewsState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is NewsState.Succeed -> {
                progressBar.visibility = View.GONE
                adapter.updateData(newsState.news)
                Log.i("data",newsState.news.toString())
            }
            is NewsState.Error -> {
                progressBar.visibility = View.GONE
                Toast.makeText(context,"擷取資料錯誤",Toast.LENGTH_SHORT).show()
                Log.e("fetchData",newsState.error.toString())
            }
        }
    }
}