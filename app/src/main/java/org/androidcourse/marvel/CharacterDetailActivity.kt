package org.androidcourse.marvel

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import kotlinx.android.synthetic.main.character_row.view.*
import kotlinx.android.synthetic.main.show_single_character.*

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_single_character)
        //recyclerView_single_character.setBackgroundColor(Color.RED)
        recyclerView_single_character.layoutManager = LinearLayoutManager(this)
        recyclerView_single_character.adapter = CharacterDetailAdapter()
    }
    private class CharacterDetailAdapter: RecyclerView.Adapter<DetailCharacterViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): DetailCharacterViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.show_single_character_row, parent,false)

            return DetailCharacterViewHolder(view)
        }
 // ca 21 min på ep 4
       // dsaklf
        override fun getItemCount(): Int {
            return 1
        }

        override fun onBindViewHolder(holder: DetailCharacterViewHolder, position: Int) {

//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    private class DetailCharacterViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}