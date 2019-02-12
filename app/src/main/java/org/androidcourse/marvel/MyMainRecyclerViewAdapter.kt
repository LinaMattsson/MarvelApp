package org.androidcourse.marvel

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


//import org.androidcourse.marvel.MainFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_main.view.*
import org.androidcourse.testmarvel.dto.MarvelCharacter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMainRecyclerViewAdapter(
) : RecyclerView.Adapter<MyMainRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    var mValues:MutableList<MarvelCharacter> = mutableListOf()

    init {
        mOnClickListener = View.OnClickListener { v ->
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
       // holder.mContentView.text = item.description
        holder.item = item

        Picasso.with(holder.context)
            .load(item.thumbnail.path +"."+ item.thumbnail.extension)
            .into(holder.mImageView)

//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View, var item: MarvelCharacter? = null) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.textView_all_character_name
//        val mContentView: TextView = mView.textView_all_character_description
        val mImageView: ImageView = mView.imageView_all_character_image
        val context = mView.context
        init {
            mView.setOnClickListener {
                val intent = Intent(mView.context, CharacterDetailActivity::class.java)
                intent.putExtra("characterId", item?.id)
                mView.context.startActivity(intent)
                println("clicked on character" + item?.name) }
            }
        }

//        override fun toString(): String {
//            return super.toString() + " '" + mContentView.text + "'"
//        }

}
