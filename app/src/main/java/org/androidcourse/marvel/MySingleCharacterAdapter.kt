package org.androidcourse.marvel

import android.support.v7.widget.RecyclerView
import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_row.view.*
import kotlinx.android.synthetic.main.show_single_character_row.view.*
import org.androidcourse.testmarvel.dto.MarvelCharacter

class MySingleCharacterAdapter ()
    : RecyclerView.Adapter<MySingleCharacterAdapter.ViewHolder>() {

    var mValues: List<MarvelCharacter> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MySingleCharacterAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_single_character_row, parent, false)
        return ViewHolder(view)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return mValues.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mValues[position]
        holder.titleToDisplay.text = item.name + "lina"
        holder.descriptionToDiaplay.text = item.description

        Picasso.with(holder.context).load(item.thumbnail.path +"."+ item.thumbnail.extension).into(holder.thumbnail)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val titleToDisplay : TextView = view.name_single_View
        val descriptionToDiaplay : TextView = view.description_single_View
        val thumbnail = view.imageView4
        val context = view.context

    }
}