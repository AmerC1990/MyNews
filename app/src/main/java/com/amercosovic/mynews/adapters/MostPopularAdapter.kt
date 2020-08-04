package com.amercosovic.mynews.adapters


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amercosovic.mynews.R
import com.amercosovic.mynews.SecondActivity
import com.amercosovic.mynews.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mostpopular_row.view.*
import kotlinx.android.synthetic.main.news_row.view.publishedDate
import kotlinx.android.synthetic.main.news_row.view.section
import kotlinx.android.synthetic.main.news_row.view.title

class MostPopularAdapter(private val mostPopular: List<Result>) :
    RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mostpopular_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mostPopular.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mostPopularData = mostPopular[position]

        if (!mostPopularData.section.isNullOrBlank() && mostPopularData.section.contains(".") && mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = mostPopularData.section.substringBefore(".").capitalize() + "." +
                    mostPopularData.section.substringAfter(".").capitalize()
        } else if (!mostPopularData.section.isNullOrBlank() && !mostPopularData.section.contains(".") && mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = mostPopularData.section.capitalize()
        } else if (!mostPopularData.section.isNullOrBlank() && mostPopularData.section.contains(".") && !mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = mostPopularData.section.substringBefore(".").capitalize() + "." +
                    mostPopularData.section.substringAfter(".").capitalize() + " > " +
                    mostPopularData.subsection
        } else if (!mostPopularData.section.isNullOrBlank() && !mostPopularData.section.contains(".") && !mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text =
                mostPopularData.section.capitalize() + " > " + mostPopularData.subsection.capitalize()
        } else if (!mostPopularData.section.isNullOrBlank() && mostPopularData.section.startsWith("N") && mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = "N.Y. Region"
        } else if (!mostPopularData.section.isNullOrBlank() && !mostPopularData.section.contains("N") && mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = mostPopularData.section.capitalize()
        } else if (!mostPopularData.section.isNullOrBlank() && mostPopularData.section.contains("N") && !mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text = "N.Y. Region" + " > " + mostPopularData.subsection.capitalize()

        } else if (!mostPopularData.section.isNullOrBlank() && !mostPopularData.section.contains("N") && !mostPopularData.subsection.isNullOrBlank()) {
            holder.section.text =
                mostPopularData.section.capitalize() + " > " + mostPopularData.subsection.capitalize()
        }



        holder.title.text = mostPopularData.title.toString()
        holder.publishedDate.text =
            mostPopularData.publishedDate.subSequence(5, 7).toString() + "/" +
                    mostPopularData.publishedDate.subSequence(8, 10).toString() + "/" +
                    mostPopularData.publishedDate.subSequence(0, 4).toString()
//        https://static01.nyt.com/images/2020/06/18/sports/17virus-sportssummer-2/17virus-sportssummer-2-mediumSquareAt3X-v2.jpg
//        https://static01.nyt.com/


        if (mostPopularData.media.isNullOrEmpty() || mostPopularData.media.filter { it.mediaMetadata != null }
                .isNullOrEmpty()) { //url.isEmpty()
            Picasso.with(holder.photo.context)
                .load(R.drawable.worldnewsicon)
                .placeholder(R.drawable.worldnewsicon)
                .error(R.drawable.worldnewsicon)
                .into(holder.photo)
        } else {
            Picasso.with(holder.photo.context)
                .load(
                    mostPopularData.media.filter { it.mediaMetadata != null }.toString()
                        .substringAfter("url=").substringBefore(",")
                )
                .error(R.drawable.worldnewsicon)
                .into(holder.photo)
        }


//        Log.d("amer", "URL: ${sportsData.webUrl}")
//        Log.d("amer", "URI: ${sportsData.uri}")
//
//        Log.d("amer", "MUL        Log.d("amer", "MULTIMEDIA$$$: ${"https://static01.nyt.com/" + sportsData.multimedia.filter { it.url.contains("images") }.toString().substringAfter("xlarge=").substringBefore(",")}")TIMEDIA$$$: ${sportsData.multimedia.filter { it.url.contains("images") } }}")
        Log.d(
            "amer",
            "MULTIMEDIA$$$: ${mostPopularData.media.filter { it.mediaMetadata != null }.toString()
                .substringAfter("url=").substringBefore(",")}"
        )


        holder.itemView.setOnClickListener {
            val url: String = mostPopularData.url.toString()
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra("url", url)
            holder.itemView.context.startActivity(intent)
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
        val photo: ImageView = itemView.mostPopularPhoto
    }

}
