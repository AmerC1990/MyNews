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
import com.amercosovic.mynews.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*

class NewsAdapter(private val news: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row,parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsData = news[position]

        holder.section.text = newsData.section.capitalize() + " > " + newsData.subsection.capitalize()
        if (holder.section.text.contains('U')) {
            holder.section.text = "U.S." + " > " + newsData.subsection.capitalize()
        }
        if (holder.section.text.startsWith('N')) {
            holder.section.text = "N.Y. Region" + " > " + newsData.subsection.capitalize()
        }
        holder.title.text = newsData.title
        holder.publishedDate.text = newsData.publishedDate.subSequence(5,7).toString() + "/" +
                newsData.publishedDate.subSequence(8,10).toString() + "/" +
                newsData.publishedDate.subSequence(0,4).toString()
//        "https://static01.nyt.com/"
        Picasso.with(holder.photo.context)
            .load(newsData.multimedia.toString().substringAfter("url=").substringBefore(","))
            .placeholder(R.drawable.worldnewsicon)
            .error(R.drawable.worldnewsicon)
            .into(holder.photo)

        Log.d("amer", "MULTIMEDIA$$$: ${newsData.multimedia.toString().substringAfter("url=").substringBefore(",")}")


        holder.itemView.setOnClickListener {
            val url: String = newsData.url.toString()
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra("url", url)
            holder.itemView.context.startActivity(intent)
        }


    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
        val photo: ImageView = itemView.photo
    }
}
