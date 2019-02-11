package org.androidcourse.marvel

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_character.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.testmarvel.dto.Comic
import org.androidcourse.testmarvel.dto.Comics
import org.androidcourse.testmarvel.dto.MarvelCharacter
import org.androidcourse.testmarvel.dto.Thumbnail
//
//class FavoritCharacterFragment : Fragment() {
//    private var listener: OnListFragmentInteractionListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_character_favorite, container, false)
//        val adapter = MyCharacterRecyclerViewAdapter()
//        view.recyclerView_character.adapter = adapter
//        view.recyclerView_character.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
//        var config = RealmConfiguration.Builder().name("character.realm").build()
//        var realm = Realm.getInstance(config)
//        var list = realm.where(CharacterToRealm::class.java).findAll()
//        var marvelList: MutableList<MarvelCharacter> = mutableListOf()
//        list.forEach { character ->
//            var comicList:List<Comic> = listOf()
//            var comics: Comics = Comics("","",comicList)
//            var thumbnail:Thumbnail = Thumbnail(character.imagePath, character.imageExtention)
//            var marvelCharacter: MarvelCharacter = MarvelCharacter(character.id, character.name.toString(),character.description.toString(),thumbnail, comics)
//            marvelList.add(marvelCharacter)
//        }
//        adapter.mValues = marvelList
//
//        return view
//    }
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//}
//class MyFavoriteCharacterRecyclerViewAdapter()
//    : RecyclerView.Adapter<MyFavoriteCharacterRecyclerViewAdapter.ViewHolder>(){
//
//    var mValues:List<CharacterToRealm> = listOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//        val cellForRow = view.inflate(R.layout.character_row, parent, false)
//
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getItemCount(): Int {
//        return mValues.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//
//inner class ViewHolder(val mView: View, var item: CharacterToRealm? = null) : RecyclerView.ViewHolder(mView) {
//
//}
//}
