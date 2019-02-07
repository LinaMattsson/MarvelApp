package org.androidcourse.marvel

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.character_row.view.*
import kotlinx.android.synthetic.main.show_single_character.view.*


import org.androidcourse.marvel.CharacterFragment.OnListFragmentInteractionListener


import kotlinx.android.synthetic.main.show_single_character_row.view.*
import org.androidcourse.testmarvel.dto.MarvelCharacter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterRecyclerViewAdapter(
) : RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder>() {

    var mValues: List<MarvelCharacter> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val cellForRow = view.inflate(R.layout.character_row, parent, false)
//        cellForRow.linearLayout_character.setOnClickListener { _->
//           val view = LayoutInflater.from(parent.context)
//            val newView = view.inflate(R.layout.show_single_character, parent, false)
//            val adapter = MySingleCharacterAdapter()
//            newView.recyclerView_single_character.adapter = adapter
//            newView.recyclerView_single_character.layoutManager = LinearLayoutManager(parent.context, LinearLayout.VERTICAL, false)
//        RetrofitClientInstance.service.getSingleCharacter(cellForRow.linearLayout_character.character_id.text.toString())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{wrapper->
//                adapter.mValues= wrapper.data.results
//                adapter.notifyDataSetChanged()
//            }
//        }
        return ViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
    return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.id


        //Do something to make it posiblie to klick on the listitems
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }



    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.character_name
        val mContentView: TextView = mView.character_id
        init{
            mView.setOnClickListener {
                val intent = Intent(mView.context, CharacterDetailActivity::class.java)
                intent.putExtra("charcterId", mView.character_id.toString())
            mView.context.startActivity(intent)
            }

        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
