package com.amercosovic.mynews.fragments

import android.R.attr.defaultValue
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
import com.amercosovic.mynews.NewsAdapter
import com.amercosovic.mynews.R
import com.amercosovic.mynews.retrofit.ApiClient.getClient
import kotlinx.android.synthetic.main.fragment_my_news_data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.GET


/**
 * A simple [Fragment] subclass.
 */
class MyNewsDataFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("amer", "Fragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getArguments() != null) {
            val apiEndPoint = this.getArguments()?.getString("mostPopularPath")
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
            fetchNewsData()
        }
    }

    suspend fun fetchNewsData() {

        try {
            val result = getClient.getNewsData("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")


            withContext(Dispatchers.Main) {
                if (result != null) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MyNewsDataFragment.context)
                        adapter = NewsAdapter(result.results)
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

