package org.androidcourse.marvel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject

import kotlinx.android.synthetic.main.show_single_character.*
import kotlinx.android.synthetic.main.show_single_character_row.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.testmarvel.dto.MarvelCharacter

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_single_character)

        //recyclerView_single_character.setBackgroundColor(Color.RED)
        recyclerView_single_character.layoutManager = LinearLayoutManager(this)
        val adapter = CharacterDetailAdapter()
        recyclerView_single_character.adapter = adapter
        val searchId = intent.getStringExtra("characterId")
        RetrofitClientInstance.service.getSingleCharacter(searchId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.result = wrapper.data.results
                adapter.notifyDataSetChanged()
            }
//
    }

    private class CharacterDetailAdapter : RecyclerView.Adapter<DetailCharacterViewHolder>() {
        var result = listOf<MarvelCharacter>()

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): DetailCharacterViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.show_single_character_row, parent, false)

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
            holder.description.text = item.description
            holder.item = item
            Picasso.with(holder.context).load(item.thumbnail.path + "." + item.thumbnail.extension)
                .into(holder.thumbnail)
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private class DetailCharacterViewHolder(view: View, var item:MarvelCharacter? = null) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name_single_View
        val description: TextView = view.description_single_View
        val context = view.context
        val thumbnail = view.imageView4


        var config = RealmConfiguration.Builder().name("character.realm").build()
        var realm = Realm.getInstance(config)


        init {
            view.button_add_favorite_character.setOnClickListener {
                try {
                    realm.beginTransaction()
                    val character = realm.createObject(CharacterToRealm::class.java, item?.id)
                    character.name = item?.name
                    character.description = item?.description
                    character.imagePath = item?.thumbnail?.path.toString()
                    character.imageExtention = item?.thumbnail?.extension.toString()
                    realm.commitTransaction()
                    Toast.makeText(this.context, "Saved as favorite", Toast.LENGTH_SHORT).show()
                }
                catch(e:Exception){
                    Toast.makeText(this.context, "Allready a favorite", Toast.LENGTH_SHORT).show()
                }
                var allCharacters = realm.where(CharacterToRealm::class.java).findAll()
                allCharacters.forEach{ character -> println(character.name)}
             realm.close()
            }
        }
    }


}