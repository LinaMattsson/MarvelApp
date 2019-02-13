package org.androidcourse.marvel

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_favoritecomic_list.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.marvel.dto.ComicToRealm
import org.androidcourse.testmarvel.dto.Comic
import org.androidcourse.testmarvel.dto.Comics
import org.androidcourse.testmarvel.dto.MarvelCharacter
import org.androidcourse.testmarvel.dto.Thumbnail


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FavoriteComicFragment.OnListFragmentInteractionListener] interface.
 */
class FavoriteComicFragment : Fragment() {

    // TODO: Customize parameters


    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoritecomic_list, container, false)
        val adapter = MyComicRecyclerViewAdapter()
        view.recyclerview_favorite_comic.adapter = adapter
        view.recyclerview_favorite_comic.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        var config = RealmConfiguration.Builder().name("comic.realm").build()
        var realm = Realm.getInstance(config)
        var list = realm.where(ComicToRealm::class.java).findAll()
        var comicList: MutableList<Comic> = mutableListOf()
        list.forEach { comic ->
            //var comics: Comics = Comics("","",comicList)
            var thumbnail: Thumbnail = Thumbnail(comic.imagePath.toString(), comic.imageExtention.toString())
            var marvelComic: Comic= Comic(comic.resourceURI.toString(), comic.title.toString(),comic.id,thumbnail,comic.description.toString())
            comicList.add(marvelComic)
        }
        adapter.mValues = comicList


        return view
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction()
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            FavoriteComicFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
