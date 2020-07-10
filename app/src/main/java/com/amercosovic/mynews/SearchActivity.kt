package com.amercosovic.mynews

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import android.widget.Toolbar
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.util.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_query_edittext.getBackground().clearColorFilter();


        actionBar?.setDisplayHomeAsUpEnabled(true)
        Enter_Begin_Date.setPaintFlags(Enter_Begin_Date.getPaintFlags())
        Enter_End_Date.setPaintFlags(Enter_End_Date.getPaintFlags())

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // TextView Clicked to show Date Picker Dialog
        Enter_Begin_Date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, Year, Month, Day ->
                    // set to textView
                    if (Day < 10 && Month < 10) {
                        Enter_Begin_Date.text =
                            "0" + Day + "/0" + Month.toInt().plus(1) + "/" + Year
                    } else if (Day < 10 && Month >= 10) {
                        Enter_Begin_Date.text = "0" + Day + "/" + Month.toInt().plus(1) + "/" + Year
                    } else if (Day >= 10 && Month < 10) {
                        Enter_Begin_Date.text = "" + Day + "/0" + Month.toInt().plus(1) + "/" + Year
                    }

                },
                year,
                month,
                day
            )
            // show dialog
            dpd.show()
        }

        Enter_End_Date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, Year, Month, Day ->
                    // set to textView
                    if (Day < 10 && Month < 10) {
                        Enter_End_Date.text = "0" + Day + "/0" + Month.toInt().plus(1) + "/" + Year
                    } else if (Day < 10 && Month >= 10) {
                        Enter_End_Date.text = "0" + Day + "/" + Month.toInt().plus(1) + "/" + Year
                    } else if (Day >= 10 && Month < 10) {
                        Enter_End_Date.text = "" + Day + "/0" + Month.toInt().plus(1) + "/" + Year
                    }

                },
                year,
                month,
                day
            )
            // show dialog
            dpd.show()
        }

        searchButton.setOnClickListener {
            if (search_query_edittext.text.isNotEmpty() && atLeastOneCheckBoxChecked()) {
                val searchQuery: String = search_query_edittext.text.toString()
                val checkedCategories = getCheckedCategories()
                val beginDate : String? = Enter_Begin_Date.text.toString().substringAfterLast("/") + Enter_Begin_Date.text.toString()?.substringAfter("/")?.substringBefore("/") +
                        Enter_Begin_Date.text.toString()?.substringBefore("/")
                val endDate : String? = Enter_End_Date.text.toString().substringAfterLast("/") + Enter_End_Date.text.toString()?.substringAfter("/")?.substringBefore("/") +
                        Enter_End_Date.text.toString()?.substringBefore("/")
                val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                intent.putExtra("query", searchQuery)
                intent.putStringArrayListExtra("checkedCategories", ArrayList(checkedCategories))
                intent.putExtra("beginDate", beginDate)
                intent.putExtra("endDate", endDate)
                startActivity(intent)
            }
        }
    }
    private fun getCheckedCategories() : List<String> = listOfNotNull(
        "Travel".takeIf { TravelTextBox.isChecked },
        "Sports".takeIf { SportsTextBox.isChecked },
        "Politics".takeIf { PoliticsTextBox.isChecked },
        "Entrepreneurs".takeIf { EntrepreneursTextBox.isChecked },
        "Business".takeIf { BusinessTextBox.isChecked },
        "Arts".takeIf { ArtsCheckBox.isChecked }
    )

    private fun atLeastOneCheckBoxChecked() = getCheckedCategories().isNotEmpty()

}




