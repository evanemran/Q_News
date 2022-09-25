package com.evanemran.qnewsbd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.qnewsbd.R
import com.evanemran.qnewsbd.models.NewsChannel
import com.evanemran.qnewsbd.models.NewsItem
import com.squareup.picasso.Picasso

class HeadlinesAdapter(var context: Context, var list: List<NewsItem>)
    : RecyclerView.Adapter<HeadlineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        return HeadlineViewHolder(LayoutInflater.from(context).inflate(R.layout.list_headlines, parent, false))
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val item: NewsItem = list[position]

        holder.textView_title.text = item.title.toString()
        holder.textView_desc.text = item.description.toString()

        if(item.content!=null) {
            Picasso.get().load(item.content!!.url).placeholder(R.mipmap.ic_launcher).into(holder.imageView_headline)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class HeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var headline_container: CardView = itemView.findViewById(R.id.headline_container)
    var textView_title: TextView = itemView.findViewById(R.id.textView_title)
    var textView_desc: TextView = itemView.findViewById(R.id.textView_desc)
    var imageView_headline: ImageView = itemView.findViewById(R.id.imageView_headline)
}