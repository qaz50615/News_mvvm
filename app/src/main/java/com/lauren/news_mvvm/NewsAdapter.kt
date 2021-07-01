package com.lauren.news_mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lauren.news_mvvm.ui.MainViewModel

class NewsAdapter(private val context:Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var data:List<NewsArticle>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data == null) return
        holder.title.text = data!![position].title
        holder.content.text = data!![position].content
        holder.date.text = data!![position].publishedAt?.replace(Regex("[TZ]")," ")
        Glide.with(context).load(data!![position].urlToImage).centerCrop().into(holder.iv)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv = itemView.findViewById<ImageView>(R.id.imageView)
        val title = itemView.findViewById<TextView>(R.id.titleTv)
        val content = itemView.findViewById<TextView>(R.id.contentTv)
        val date = itemView.findViewById<TextView>(R.id.dateTv)
    }

    fun updateData(data:List<NewsArticle>) {
        this.data = data
        notifyDataSetChanged()
    }

}
