package com.amercosovic.mynews.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amercosovic.mynews.R
import com.amercosovic.mynews.SecondActivity
import com.amercosovic.mynews.model.Doc
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.publishedDate
import kotlinx.android.synthetic.main.news_row.view.section
import kotlinx.android.synthetic.main.news_row.view.title
import kotlinx.android.synthetic.main.sports_row.view.*

class SportsAdapter(private val sports: List<Doc>) : RecyclerView.Adapter<SportsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sports_row,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = sports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportsData = sports[position]

        holder.section.text = sportsData.sectionName.toString()
        holder.title.text = sportsData.headline.main.toString()
        holder.publishedDate.text = sportsData.pubDate.subSequence(5, 7).toString() + "/" +
                sportsData.pubDate.subSequence(8, 10).toString() + "/" +
                sportsData.pubDate.subSequence(0, 4).toString()
//        https://static01.nyt.com/images/2020/06/18/sports/17virus-sportssummer-2/17virus-sportssummer-2-mediumSquareAt3X-v2.jpg
//        https://static01.nyt.com/



        Picasso.with(holder.photo.context)
            .load("https://static01.nyt.com/" + sportsData.multimedia.filter { it.url.contains("images") }.toString().substringAfter("xlarge=").substringBefore(","))
            .placeholder(R.drawable.worldnewsicon)
            .error(R.drawable.worldnewsicon)
            .into(holder.photo)


//        Log.d("amer", "URL: ${sportsData.webUrl}")
//        Log.d("amer", "URI: ${sportsData.uri}")
//
//        Log.d("amer", "MUL        Log.d("amer", "MULTIMEDIA$$$: ${"https://static01.nyt.com/" + sportsData.multimedia.filter { it.url.contains("images") }.toString().substringAfter("xlarge=").substringBefore(",")}")TIMEDIA$$$: ${sportsData.multimedia.filter { it.url.contains("images") } }}")
//        Log.d("amer", "MULTIMEDIA$$$: ${sportsData.multimedia.toString().substringAfter("url:")}")



        holder.itemView.setOnClickListener {
            val url: String = sportsData.webUrl.toString()
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra("url", url)
            holder.itemView.context.startActivity(intent)
        }
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val section: TextView = itemView.section
        val publishedDate: TextView = itemView.publishedDate
        val title: TextView = itemView.title
        val photo: ImageView = itemView.sportsPhoto
    }

}
