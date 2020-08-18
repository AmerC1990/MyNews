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
import com.amercosovic.mynews.R
import com.amercosovic.mynews.ReadNotifiedArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*

class NotifiedNewsAdapter(private val notifiedNewsResults: List<Doc>) :
    RecyclerView.Adapter<NotifiedNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notified_news, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = notifiedNewsResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notifiedNewsData = notifiedNewsResults[position]

        if (!notifiedNewsData.sectionName.isNullOrBlank() && notifiedNewsData.sectionName.contains(
                "us"
            ) && notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "U.S."
        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && !notifiedNewsData.sectionName.contains(
                "us"
            ) && notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = notifiedNewsData.sectionName.capitalize()
        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && notifiedNewsData.sectionName.contains(
                "us"
            ) && !notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "U.S." + " > " + notifiedNewsData.subsectionName.capitalize()

        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && !notifiedNewsData.sectionName.contains(
                "us"
            ) && !notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                notifiedNewsData.sectionName.capitalize() + " > " + notifiedNewsData.subsectionName.capitalize()
        }
        //

        else if (!notifiedNewsData.sectionName.isNullOrBlank() && notifiedNewsData.sectionName.startsWith(
                "N"
            ) && notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = "N.Y. Region"
        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && !notifiedNewsData.sectionName.startsWith(
                "N"
            ) && notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text = notifiedNewsData.sectionName.capitalize()
        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && notifiedNewsData.sectionName.startsWith(
                "N"
            ) && !notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                "N.Y. Region" + " > " + notifiedNewsData.subsectionName.capitalize()

        } else if (!notifiedNewsData.sectionName.isNullOrBlank() && !notifiedNewsData.sectionName.startsWith(
                "N"
            ) && !notifiedNewsData.subsectionName.isNullOrBlank()
        ) {
            holder.section.text =
                notifiedNewsData.sectionName.capitalize() + " > " + notifiedNewsData.subsectionName.capitalize()
        }

        holder.title.text = notifiedNewsData.headline.main.toString()
        holder.publishedDate.text = notifiedNewsData.pubDate.subSequence(5, 7).toString() + "/" +
                notifiedNewsData.pubDate.subSequence(8, 10).toString() + "/" +
                notifiedNewsData.pubDate.subSequence(0, 4).toString()



        if (notifiedNewsData.multimedia.isNullOrEmpty()) { //url.isEmpty()
            Picasso.with(holder.photo.context)
                .load(R.drawable.worldnewsicon)
                .placeholder(R.drawable.worldnewsicon)
                .error(R.drawable.worldnewsicon)
                .into(holder.photo)
        } else {
            Picasso.with(holder.photo.context)
                .load(
                    "https://static01.nyt.com/" + notifiedNewsData.multimedia.toString()
                        .substringAfter("url=").substringBefore(",")
                )
                .error(R.drawable.worldnewsicon)
                .into(holder.photo) //this is your ImageView
        }
        holder.itemView.setOnClickListener {
            val url: String = notifiedNewsData.webUrl.toString()
            val intent = Intent(holder.itemView.context, ReadNotifiedArticle::class.java)
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

