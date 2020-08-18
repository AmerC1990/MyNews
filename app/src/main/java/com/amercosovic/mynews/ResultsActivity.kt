package com.amercosovic.mynews

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.adapters.SearchResultsAdapter
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // get bundle, and if bundle is NOT empty,  API call and display data in
        // reyclerview --- if api call result is empty or null, show dialog box informing user

        val bundle: Bundle? = intent.extras
        val query = bundle!!.getString("query")

        if (!query.isNullOrEmpty()) {
                        lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResult(
                    query = query,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                    withContext(Main) {
                    if (result.response.docs.isNotEmpty()) {
                        pbarInResults.isVisible = false
                        SearchResultsRecyclerView.apply {
                            layoutManager = LinearLayoutManager(this@ResultsActivity)
                            adapter = SearchResultsAdapter(result.response.docs)
                        }
                    } else {
                        // build alert dialog
                        val dialogBuilder = AlertDialog.Builder(this@ResultsActivity)

                        // set message of alert dialog
                        dialogBuilder.setMessage("Your search results are empty.")
                            // if the dialog is cancelable
                            .setCancelable(true)
                            // positive button text and action
                            .setPositiveButton(
                                "Close",
                                DialogInterface.OnClickListener { dialog, id ->
                                    finish()
                                })
                        // create dialog box
                        val alert = dialogBuilder.create()
                        // set title for alert dialog box
                        alert.setTitle("Alert")
                        // show alert dialog
                        alert.show()
                    }
                }
            }
        }



    }
}
