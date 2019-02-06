package org.androidcourse.marvel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    override fun onBindViewHolder(holder: MySingleCharacterAdapter.ViewHolder, position: Int) {
        var item = mValues[position]
        holder.TitleToDisplay.text = item.name
        holder.IdToDisplay.text = item.id
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    inner class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val TitleToDisplay : TextView = view.name_single_View
        val IdToDisplay : TextView = view.description_single_View

    }
}