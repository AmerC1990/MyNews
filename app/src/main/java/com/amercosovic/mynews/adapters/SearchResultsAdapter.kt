package com.amercosovic.mynews.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amercosovic.dummynews2.modelwithmandatorysearchonly.Doc
import com.amercosovic.mynews.R
import com.amercosovic.mynews.SecondActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*

class SearchResultsAdapter(private val searchResults: List<Doc>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_row, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = searchResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResultsData = searchResults[position]

        if (!searchResultsData.sectionName.isNullOrBlank() && searchResultsData.sectionName.contains(
                "us"
            ) && searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "U.S."
        } else if (!searchResultsData.sectionName.isNullOrBlank() && !searchResultsData.sectionName.contains(
                "us"
            ) && searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = searchResultsData.sectionName.capitalize()
        } else if (!searchResultsData.sectionName.isNullOrBlank() && searchResultsData.sectionName.contains(
                "us"
            ) && !searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "U.S." + " > " + searchResultsData.subsectionName.capitalize()

        } else if (!searchResultsData.sectionName.isNullOrBlank() && !searchResultsData.sectionName.contains(
                "us"
            ) && !searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                searchResultsData.sectionName.capitalize() + " > " + searchResultsData.subsectionName.capitalize()
        }
        //

        else if (!searchResultsData.sectionName.isNullOrBlank() && searchResultsData.sectionName.startsWith(
                "N"
            ) && searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "N.Y. Region"
        } else if (!searchResultsData.sectionName.isNullOrBlank() && !searchResultsData.sectionName.startsWith(
                "N"
            ) && searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = searchResultsData.sectionName.capitalize()
        } else if (!searchResultsData.sectionName.isNullOrBlank() && searchResultsData.sectionName.startsWith(
                "N"
            ) && !searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                "N.Y. Region" + " > " + searchResultsData.subsectionName.capitalize()

        } else if (!searchResultsData.sectionName.isNullOrBlank() && !searchResultsData.sectionName.startsWith(
                "N"
            ) && !searchResultsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                searchResultsData.sectionName.capitalize() + " > " + searchResultsData.subsectionName.capitalize()
        }

        holder.title.text = searchResultsData.headline.main.toString()

        holder.publishedDate.text = searchResultsData.pubDate.subSequence(5, 7).toString() + "/" +
                searchResultsData.pubDate.subSequence(8, 10).toString() + "/" +
                searchResultsData.pubDate.subSequence(0, 4).toString()

        if (searchResultsData.multimedia.isNullOrEmpty()) { //url.isEmpty()
            Picasso.with(holder.photo.context)
                .load(R.drawable.worldnewsicon)
                .placeholder(R.drawable.worldnewsicon)
                .error(R.drawable.worldnewsicon)
                .into(holder.photo)
        } else {
            Picasso.with(holder.photo.context)
                .load(
                    "https://static01.nyt.com/" + searchResultsData.multimedia.toString()
                        .substringAfter("url=").substringBefore(",")
                )
                .error(R.drawable.worldnewsicon)
                .into(holder.photo)
        }
        holder.itemView.setOnClickListener {
            val url: String = searchResultsData.webUrl.toString()
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra("url", url)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
        val photo: ImageView = itemView.photo
    }
}
