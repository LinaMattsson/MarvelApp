package org.androidcourse.marvel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.show_single_comic.*
import kotlinx.android.synthetic.main.show_single_comic_row.view.*
import org.androidcourse.marvel.dto.ComicToRealm
import org.androidcourse.testmarvel.dto.Comic

class ComicDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_single_comic)

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
            holder.item = item
            Picasso.with(holder.context).load(item.thumbnail.path+"."+item.thumbnail.extension).into(holder.thumbnail)
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    private class DetailComicViewHolder(view: View, var item:Comic? = null) : RecyclerView.ViewHolder(view){
        val name: TextView = view.textView_comic_title
        val description: TextView = view.textView_comic_desdription
        val context = view.context
        val thumbnail = view.imageView_single_comic

        var config = RealmConfiguration.Builder().name("comic.realm").build()
        var realm = Realm.getInstance(config)

        init {
            view.button_add_favorite_comic.setOnClickListener {

                try {
                    realm.beginTransaction()
                    val comic = realm.createObject(ComicToRealm::class.java, item?.id)
                    comic.title = item?.title
                    comic.description = item?.description
                    comic.imagePath = item?.thumbnail?.path.toString()
                    comic.imageExtention = item?.thumbnail?.extension.toString()
                    comic.resourceURI = item?.resourceURI.toString()
                    realm.commitTransaction()
                    Toast.makeText(this.context, "Saved as favorite", Toast.LENGTH_SHORT).show()
                }
                catch(e:Exception){
                    Toast.makeText(this.context, "Allready a favorite", Toast.LENGTH_SHORT).show()
                }
                var allComics = realm.where(ComicToRealm::class.java).findAll()
                allComics.forEach{ comic -> println(comic.title)}
                realm.close()
            }
        }
    }
}