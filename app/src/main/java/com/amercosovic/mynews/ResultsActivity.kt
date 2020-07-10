package com.amercosovic.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.adapters.NewsAdapter
import com.amercosovic.mynews.adapters.SearchResultsAdapter
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.fragment_my_news_data.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val bundle: Bundle? = intent.extras
        val query = bundle!!.getString("query")
        val category = bundle!!.getStringArrayList("checkedCategories").orEmpty()
        val beginDate = bundle!!.getString("beginDate")
        val endDate = bundle!!.getString("endDate")
        if (category.count() == 1 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith1Checkbox(query = query!!,
                    category1 = category.component1(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  category.component1().toString())
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 2 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith2Checkbox(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  "Box1: ${category.component1().toString()} Box2:${category.component2().toString()} ")
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 3 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith3Checkbox(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  "Box1: ${category.component1().toString()} Box2:${category.component2().toString()} ")
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 4 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith4Checkbox(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  "Box1: ${category.component1().toString()} Box2:${category.component2().toString()} ")
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 5 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith5Checkbox(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  "Box1: ${category.component1().toString()} Box2:${category.component2().toString()} ")
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 6 && beginDate.isNullOrEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith6Checkbox(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    category6 = category.last(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    Toast.makeText(this@ResultsActivity, category.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("ResultActivity",  "Box1: ${category.component1().toString()} Box2:${category.component2().toString()} " +
//                    "Box3:${category.component3().toString()} Box4:${category.component4().toString()} " +
//                    "Box5: ${category.component5().toString()} Box6: ${category.last().toString()}")
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 1 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith1CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 2 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith2CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 3 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith3CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 4 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith4CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 5 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith5CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 6 && beginDate!!.isNotEmpty() && endDate.isNullOrEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith6CheckboxAndBeginDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    category6 = category.last(),
                    begin_date = beginDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 1 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith1CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 2 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith2CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 3 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith3CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 4 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith4CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 5 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith5CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 6 && beginDate.isNullOrEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith6CheckboxAndEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    category6 = category.last(),
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 1 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith1CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 2 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith2CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 3 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith3CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 4 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith4CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }

        else if (category.count() == 5 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith5CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }
        else if (category.count() == 6 && beginDate!!.isNotEmpty() && endDate!!.isNotEmpty()) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith6CheckboxBeginDateEndDate(query = query!!,
                    category1 = category.component1(),
                    category2 = category.component2(),
                    category3 = category.component3(),
                    category4 = category.component4(),
                    category5 = category.component5(),
                    category6 = category.last(),
                    begin_date = beginDate,
                    end_date = endDate,
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")

                withContext(Main) {
                    SearchResultsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ResultsActivity)
                        adapter = SearchResultsAdapter(result.response.docs)
                    }
                }
            }
        }










    }
}
