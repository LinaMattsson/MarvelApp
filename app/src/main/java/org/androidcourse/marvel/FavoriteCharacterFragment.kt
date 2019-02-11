package org.androidcourse.marvel

import android.content.Context
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
import kotlinx.android.synthetic.main.fragment_favorite_character.view.*
import org.androidcourse.marvel.dto.CharacterToRealm
import org.androidcourse.testmarvel.dto.Comic
import org.androidcourse.testmarvel.dto.Comics
import org.androidcourse.testmarvel.dto.MarvelCharacter
import org.androidcourse.testmarvel.dto.Thumbnail


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoriteCharacterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoriteCharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FavoriteCharacterFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_character, container, false)
        val adapter = MyCharacterRecyclerViewAdapter()
        view.recyclerView_favorite_character.adapter = adapter
        view.recyclerView_favorite_character.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        var config = RealmConfiguration.Builder().name("character.realm").build()
        var realm = Realm.getInstance(config)
        var list = realm.where(CharacterToRealm::class.java).findAll()
        var marvelList: MutableList<MarvelCharacter> = mutableListOf()
        list.forEach { character ->
            var comicList:List<Comic> = listOf()
            var comics: Comics = Comics("","",comicList)
            var thumbnail: Thumbnail = Thumbnail(character.imagePath, character.imageExtention)
            var marvelCharacter: MarvelCharacter = MarvelCharacter(character.id, character.name.toString(),character.description.toString(),thumbnail, comics)
            marvelList.add(marvelCharacter)
        }
        adapter.mValues = marvelList

        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_favorite_character, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteCharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FavoriteCharacterFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
