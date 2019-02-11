package org.androidcourse.marvel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.show_single_comic.*
import kotlinx.android.synthetic.main.show_single_comic_row.view.*
import org.androidcourse.testmarvel.dto.Comic

class ComicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_single_comic)
        //recyclerView_single_character.setBackgroundColor(Color.RED)

        recyclerView_single_comic.layoutManager = LinearLayoutManager(this)
        val adapter = ComicDetailAdapter()
        recyclerView_single_comic.adapter = adapter
        val searchId= intent.getStringExtra("comicId") //kan vara fel stavat
        RetrofitClientInstance.service.getSingleComic(searchId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ wrapper ->
                adapter.result = wrapper.data.results
                adapter.notifyDataSetChanged()
            }
//
    }
    private class ComicDetailAdapter: RecyclerView.Adapter<DetailComicViewHolder>(){
        var result = listOf<Comic>()

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): DetailComicViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.show_single_comic_row, parent,false)

            return DetailComicViewHolder(view)
        }
        // ca 21 min på ep 4
        // dsaklf
        override fun getItemCount(): Int {
            return result.size
        }

        override fun onBindViewHolder(holder: DetailComicViewHolder, position: Int) {
            var item = result[position]
            holder.name.text = item.title
            holder.description.text = item.description
            Picasso.with(holder.context).load(item.thumbnail.path+"."+item.thumbnail.extension).into(holder.thumbnail)
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    private class DetailComicViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.textView_comic_title
        val description: TextView = view.textView_comic_desdription
        val context = view.context
        val thumbnail = view.imageView_single_comic

    }
}