package com.amercosovic.mynews.adapters


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amercosovic.dummynews2.modelwithmandatorysearchonly.Doc
import com.amercosovic.dummynews2.modelwithmandatorysearchonly.Response
import com.amercosovic.mynews.R
import com.amercosovic.mynews.SecondActivity
import com.amercosovic.mynews.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*

class SearchResultsAdapter(private val searchResults: List<Doc>) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_row,parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = searchResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResultsData = searchResults[position]

        holder.section.text = searchResultsData.sectionName

        holder.title.text = searchResultsData.snippet.toString()

        holder.publishedDate.text = searchResultsData.pubDate.subSequence(5,7).toString() + "/" +
                searchResultsData.pubDate.subSequence(8,10).toString() + "/" +
                searchResultsData.pubDate.subSequence(0,4).toString()
//        "https://static01.nyt.com/"

        Picasso.with(holder.photo.context)
            .load("https://static01.nyt.com/" + searchResultsData.multimedia.toString().substringAfter("url=").substringBefore(","))
            .placeholder(R.drawable.worldnewsicon)
            .error(R.drawable.worldnewsicon)
            .into(holder.photo)
//
//        Log.d("amer", "SEARCH: ${searchResultsData.multimedia.toString().substringAfter("url=").substringBefore(",")}")


//        holder.itemView.setOnClickListener {
//            val url: String = searchResultsData.url.toString()
//            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
//            intent.putExtra("url", url)
//            holder.itemView.context.startActivity(intent)
//        }


    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
        val photo: ImageView = itemView.photo
    }
}
