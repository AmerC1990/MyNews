package com.amercosovic.mynews.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.MainActivity.PATHS.MOST_POPULAR
import com.amercosovic.mynews.MainActivity.PATHS.SPORTS
import com.amercosovic.mynews.MainActivity.PATHS.TOP_STORIES
import com.amercosovic.mynews.adapters.MostPopularAdapter
import com.amercosovic.mynews.adapters.NewsAdapter
import com.amercosovic.mynews.R
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

    lateinit var RECEIVED_ENDPOINT: String

    companion object {
        val KEY_ENDPOINT = "key_endpoint"

        fun newInstance(endPoint: String): MyNewsDataFragment {
            val args = Bundle()
            args.putString(KEY_ENDPOINT, endPoint)
            val fragment = MyNewsDataFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("amer", "Fragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            RECEIVED_ENDPOINT = bundle.getString(KEY_ENDPOINT, MOST_POPULAR)
            RECEIVED_ENDPOINT = bundle.getString(KEY_ENDPOINT, SPORTS)
        }
        Log.d("amer", "Fragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my_news_data, container, false)
        Log.d("amer", "Fragment onCreateView")
        // Inflate the layout for this fragment
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("amer", "Fragment onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("amer", "Fragment onStart")
        super.onStart()

        lifecycleScope.launch(Dispatchers.IO) {
            fetchNewsData(RECEIVED_ENDPOINT)
        }
    }

    suspend fun fetchNewsData(receivedEndpoint: String) {

        try {
            var result: NewsResponse? = null
            var mostPopularResult: MostPopular? = null
            var sportsResult: Sports? = null


            when (receivedEndpoint) {
                TOP_STORIES -> result = getClient.getTopNews("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
                MOST_POPULAR -> mostPopularResult =
                    getClient.getPopularNews("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
                SPORTS -> sportsResult = getClient.getSports("sports", "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
            }
            withContext(Main) {
                if (result != null) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MyNewsDataFragment.context)
                        adapter =
                            NewsAdapter(result.results)
                    }
                }
                if (sportsResult != null) {
                    sportsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MyNewsDataFragment.context)
                        adapter = SportsAdapter(
                            sportsResult.response.docs
                        )
                    }
                }
                if (mostPopularResult != null) {
                    mostPopularRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MyNewsDataFragment.context)
                        adapter =
                            MostPopularAdapter(
                                mostPopularResult.results
                            )
                    }
                }

            }

        } catch (e: Exception) {
            Log.e("Error", "Error occurred ${e.message}")

            //Toasting from Main thread because UI can't be modified IN ANY WAY from an IO/Background thread
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error occured ${e.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        Log.d("amer", "Fragment onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("amer", "Fragment onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("amer", "Fragment onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("amer", "Fragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("amer", "Fragment onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("amer", "Fragment onDetach")
        super.onDetach()
    }

}

