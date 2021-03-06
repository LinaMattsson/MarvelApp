package org.androidcourse.marvel

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlinx.android.synthetic.main.character_row.view.*
import kotlinx.android.synthetic.main.show_single_character.view.*


import org.androidcourse.marvel.CharacterFragment.OnListFragmentInteractionListener


import kotlinx.android.synthetic.main.show_single_character_row.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.testmarvel.dto.MarvelCharacter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterRecyclerViewAdapter(
) : RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder>() {

    var mValues: List<MarvelCharacter> = listOf()

    val list:RealmResults<CharacterToRealm>

    private val realmListener = object: RealmChangeListener<RealmResults<CharacterToRealm>> {
        override fun onChange(t: RealmResults<CharacterToRealm>) {
            notifyDataSetChanged()
        }
    }

    init {
        var config = RealmConfiguration.Builder().name("character.realm").build()
        var realm = Realm.getInstance(config)
        list = realm.where(CharacterToRealm::class.java).findAll()
        list.addChangeListener(realmListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val cellForRow = view.inflate(R.layout.character_row, parent, false)
        return ViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
    return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.id
        holder.item = item
        val thumbnail = holder.mView.imageView_character_list
        Picasso.with(holder.mView.context)
            .load(item.thumbnail.path +"."+ item.thumbnail.extension)
            .into(thumbnail)


        var favorite:Boolean = false

        list.forEach { character ->
            if(character.id==item.id){favorite=true}
        }
        if(!favorite) {
            holder.star.visibility = View.GONE
        }else
        {
            holder.star.visibility = View.VISIBLE
        }

        //Do something to make it posiblie to klick on the listitems
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }



    inner class ViewHolder(val mView: View, var item: MarvelCharacter? = null) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.character_name
        val mContentView: TextView = mView.character_id
        val star = mView.imageButton_favorite_character

//        val mImageView: ImageView = mView.imageView_character_list
        init{
            mView.setOnClickListener {
                val intent = Intent(mView.context, CharacterDetailActivity::class.java)
                intent.putExtra("characterId", item?.id)
            mView.context.startActivity(intent)
            }

        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
