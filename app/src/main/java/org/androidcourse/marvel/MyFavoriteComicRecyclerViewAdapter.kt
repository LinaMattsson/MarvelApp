package org.androidcourse.marvel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.comic_row.view.*
import org.androidcourse.marvel.FavoriteComicFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_favoritecomic.view.*
import org.androidcourse.testmarvel.dto.Comic

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
//class MyFavoriteComicRecyclerViewAdapter(
//) : RecyclerView.Adapter<MyFavoriteComicRecyclerViewAdapter.ViewHolder>() {
//
//    private val mOnClickListener: View.OnClickListener
//    var mValues:MutableList<Comic> = mutableListOf()
//
//    init {
//        mOnClickListener = View.OnClickListener { v ->
//
//            // Notify the active callbacks interface (the activity, if the fragment is attached to
//            // one) that an item has been selected.
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.comic_row, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = mValues[position]
//        holder.mIdView.text = item.title
//        holder.mContentView.text = item.description
//        holder.item = item
//
//
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
//    }
//
//    override fun getItemCount(): Int = mValues.size
//
//    inner class ViewHolder(val mView: View, var item:Comic? = null) : RecyclerView.ViewHolder(mView) {
//        val mIdView: TextView = mView.item_number
//        val mContentView: TextView = mView.content
//        val mImageView: ImageView = mView.imageView_all_comics
//        val context = mView.context
//
//        override fun toString(): String {
//            return super.toString() + " '" + mContentView.text + "'"
//        }
//    }
//}
