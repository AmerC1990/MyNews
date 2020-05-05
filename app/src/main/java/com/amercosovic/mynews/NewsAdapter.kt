package com.amercosovic.mynews


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amercosovic.mynews.model.News
import kotlinx.android.synthetic.main.news_row.view.*

class NewsAdapter(private val news: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsData = news[position]
//        Log.d("daniel", "phone number? ${user.phone}")
//        holder.firstName.text = user.name
//        holder.lastName.text = user.website



        holder.section.text = newsData.section.capitalize() + " > " + newsData.subsection.capitalize()
        if (holder.section.text.contains('U')) {
            holder.section.text = "U.S." + " > " + newsData.subsection.capitalize()
        }
        if (holder.section.text.startsWith('N')) {
            holder.section.text = "N.Y. Region" + " > " + newsData.subsection.capitalize()
        }
        holder.title.text = newsData.title
//        holder.section.text = newsData.section
//        holder.subSection.text = newsData.subsection
        holder.publishedDate.text = newsData.publishedDate.substringBefore('T')
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
//        val subSection: TextView = itemView.subSection
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
//        val firstName: TextView = itemView.firstName
//        val lastName: TextView = itemView.lastName
    }

}
