package org.androidcourse.marvel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.character_row.view.*

class CharacterAdapter: RecyclerView.Adapter<CustomViewHolder>() {
    val characters = listOf("hjälte", "skurk", "offer")

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.character_row, parent, false)
        return CustomViewHolder(cellForRow)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val character = characters.get(position)
        holder.view.character_name.text = character
    }
}


class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view){

}