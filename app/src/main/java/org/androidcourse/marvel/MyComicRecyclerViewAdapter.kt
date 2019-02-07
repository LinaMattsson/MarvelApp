package org.androidcourse.marvel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_row.view.*
import kotlinx.android.synthetic.main.comic_row.view.*
import org.androidcourse.testmarvel.dto.Comic

class MyComicRecyclerViewAdapter ()
    : RecyclerView.Adapter<MyComicRecyclerViewAdapter.ViewHolder>() {

    var mValues: List<Comic> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyComicRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_row, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mValues[position]
        holder.TitleDisplay.text = item.title
        holder.IdDisplay.text = item.id

        Picasso.with(holder.context)
            .load(item.thumbnail.path +"."+ item.thumbnail.extension)
            .into(holder.thumbnail)

    }

     inner class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val TitleDisplay: TextView = view.textView_comic_title
         val IdDisplay: TextView = view.textView_comic_id
         val thumbnail = view.imageView2
         val context = view.context

         override fun toString(): String {
             return super.toString() + " '" + TitleDisplay.text + "'"
         }
    }
}