package com.amercosovic.mynews.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.MainActivity.CONSTANTS.API_KEY
import com.amercosovic.mynews.MainActivity.CONSTANTS.MOST_POPULAR
import com.amercosovic.mynews.MainActivity.CONSTANTS.SPORTS
import com.amercosovic.mynews.MainActivity.CONSTANTS.TOP_STORIES
import com.amercosovic.mynews.R
import com.amercosovic.mynews.adapters.MostPopularAdapter
import com.amercosovic.mynews.adapters.NewsAdapter
import com.amercosovic.mynews.adapters.SportsAdapter
import com.amercosovic.mynews.model.MostPopular
import com.amercosovic.mynews.model.NewsResponse
import com.amercosovic.mynews.model.Sports
import com.amercosovic.mynews.retrofit.ApiClient
import com.amercosovic.mynews.retrofit.ApiClient.getClient
import kotlinx.android.synthetic.main.fragment_my_news_data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 */
class MyNewsDataFragment : Fragment() {

    var RECEIVED_ENDPOINT: String? = null

    companion object {
        val KEY_ENDPOINT = "key_endpoint"

        // fun to pass EndPoint Instance to Fragment
        fun newInstance(endPoint: String): MyNewsDataFragment {
            val args = Bundle()
            args.putString(KEY_ENDPOINT, endPoint)
            val fragment = MyNewsDataFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            RECEIVED_ENDPOINT = bundle.getString(KEY_ENDPOINT, null)
        }
        Log.d("amer", "Fragment onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_news_data, container, false)
    }
    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        lifecycleScope.launch(Dispatchers.IO) {
            fetchNewsData(RECEIVED_ENDPOINT)
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    // suspend function to make api call and populate recyclerview depending on if "top stories", "most
    // popular" or "sports" tabs have been selected
    // Key Endoint is passed as parameter to this fun

    private suspend fun fetchNewsData(type: String?) {

        try {
            when (type) {
                TOP_STORIES -> {
                    val response = getClient.getTopNews(API_KEY)
                    withContext(Main) {
                        pbarInFragment.isVisible = false
                        val adapter = NewsAdapter(response.results)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
                MOST_POPULAR -> {
                    val response = getClient.getPopularNews(API_KEY)
                    withContext(Main) {
                        pbarInFragment.isVisible = false
                        val adapter = MostPopularAdapter(response.results)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
                SPORTS -> {
                    val response = getClient.getSports("sports", API_KEY)
                    withContext(Main) {
                        pbarInFragment.isVisible = false
                        val adapter = SportsAdapter(response.response.docs)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Error", "Error occurred ${e.message}")
            //Toasting from Main thread because UI can't be modified IN ANY WAY from an IO/Background thread
            withContext(Main) {
                Toast.makeText(context, "Error occurred ${e.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    fun reloadNews(type: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            fetchNewsData(type)
        }
    }
}

