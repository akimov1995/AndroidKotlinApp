package com.example.testapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.ui.activities.NewsViewActivity
import com.example.testapp.mvp.model.Result
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val resultList: MutableList<Result> = ArrayList()

    fun setData(result: List<Result>) {
        resultList.clear()
        resultList.addAll(result)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return resultList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
        holder.itemView.setOnClickListener { v: View? ->
            showNewsActivity(
                v!!.context,
                resultList[position].url
            )
        }
    }

    private fun showNewsActivity(context: Context, parameter: String) {
        val intent = Intent(context, NewsViewActivity::class.java)
        intent.putExtra("url", parameter)
        startActivity(context, intent, null)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(model: Result) {
            title.text = model.title
            date.text = model.published_date.substring(0, 10)
            val imageIndex: Int = 1

            if (model.multimedia != null) {
                Picasso.get()
                    .load(model.multimedia.get(imageIndex).url)
                    .fit()
                    .into(image)
            } else {
                image.setImageResource(R.drawable.image_not_found)
            }
        }
    }
}