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
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.character_row.view.*
import kotlinx.android.synthetic.main.fragment_character.view.*
import kotlinx.android.synthetic.main.show_single_character.*
import kotlinx.android.synthetic.main.show_single_character_row.view.*
import org.androidcourse.testmarvel.dto.MarvelCharacter

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_single_character)
        //recyclerView_single_character.setBackgroundColor(Color.RED)
        recyclerView_single_character.layoutManager = LinearLayoutManager(this)
        val adapter =CharacterDetailAdapter()
        recyclerView_single_character.adapter = adapter
        val searchId= intent.getStringExtra("characterId")
        RetrofitClientInstance.service.getSingleCharacter(searchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ wrapper ->
                    adapter.result = wrapper.data.results
                    adapter.notifyDataSetChanged()
                }
//
    }
    private class CharacterDetailAdapter: RecyclerView.Adapter<DetailCharacterViewHolder>(){
        var result = listOf<MarvelCharacter>()

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): DetailCharacterViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.show_single_character_row, parent,false)

            return DetailCharacterViewHolder(view)
        }
 // ca 21 min på ep 4
       // dsaklf
        override fun getItemCount(): Int {
            return result.size
        }

        override fun onBindViewHolder(holder: DetailCharacterViewHolder, position: Int) {
            var item = result[position]
                holder.name.text = item.name
            holder.id.text = item.description
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    private class DetailCharacterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.name_single_View
        val id: TextView = view.description_single_View
    }
}