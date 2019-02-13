package org.androidcourse.marvel

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.comic_row.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.marvel.dto.ComicToRealm
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
        holder.item = item

        Picasso.with(holder.context)
            .load(item.thumbnail.path +"."+ item.thumbnail.extension)
            .into(holder.thumbnail)

        var config = RealmConfiguration.Builder().name("comic.realm").build()
        var realm = Realm.getInstance(config)
        var list = realm.where(ComicToRealm::class.java).findAll()
        var favorite:Boolean = false

        list.forEach { comic ->
            if(comic.id==item.id){favorite=true}}
        if(!favorite) {
            holder.star.visibility = View.GONE
        }else
        {
            holder.star.visibility = View.VISIBLE
        }
    }

     inner class ViewHolder (view: View, var item: Comic? = null): RecyclerView.ViewHolder(view) {
        val TitleDisplay: TextView = view.textView_comic_title
         val IdDisplay: TextView = view.textView_comic_id
         val thumbnail = view.imageView_all_comics
         val context = view.context
         val star = view.imageButton_star_comic

         init{
             view.setOnClickListener {
                 val intent = Intent(view.context, ComicDetailActivity::class.java)
                 intent.putExtra("comicId", item?.id)
                 view.context.startActivity(intent)
             }
         }
         override fun toString(): String {
             return super.toString() + " '" + TitleDisplay.text + "'"
         }
    }
}