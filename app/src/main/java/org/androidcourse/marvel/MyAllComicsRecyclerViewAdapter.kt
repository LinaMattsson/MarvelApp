package org.androidcourse.marvel

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.RealmConfiguration


import org.androidcourse.marvel.AllComicsFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_allcomics.view.*
import org.androidcourse.marvel.dto.ComicToRealm
import org.androidcourse.testmarvel.dto.Comic

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAllComicsRecyclerViewAdapter(
) : RecyclerView.Adapter<MyAllComicsRecyclerViewAdapter.ViewHolder>() {

    var mValues: MutableList<Comic> = mutableListOf()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->

            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_allcomics, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.title
        //holder.mContentView.text = item.description
        holder.item = item

        var config = RealmConfiguration.Builder().name("comic.realm").build()
        var realm = Realm.getInstance(config)
        var list = realm.where(ComicToRealm::class.java).findAll()
        var favorite:Boolean = false

        list.forEach { comic -> if(comic.id==item.id){favorite=true}}
        if(!favorite) {
            holder.star.visibility = View.GONE
        }
        else{
            holder.star.visibility = View.VISIBLE
        }

        Picasso.with(holder.context)
            .load(item.thumbnail.path +"."+ item.thumbnail.extension)
            .into(holder.image)

//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View, var item: Comic? = null) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.textView_all_comics_name
       // val mContentView: TextView = mView.textView_all_comics_description
        val context = mView.context
        val image: ImageView = mView.imageView_all_comics
        val star = mView.imageButton_star_allcomics


        init{
            mView.setOnClickListener {
                val intent = Intent(mView.context, ComicDetailActivity::class.java)
                intent.putExtra("comicId", item?.id)
                mView.context.startActivity(intent)
            }
        }

//        override fun toString(): String {
//           // return super.toString() + " '" + mContentView.text + "'"
//        }
    }
}
