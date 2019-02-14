package org.androidcourse.marvel

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_character.view.*
import org.androidcourse.marvel.dto.CharacterToRealm


import org.androidcourse.testmarvel.dto.MarvelCharacter

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CharacterFragment.OnListFragmentInteractionListener] interface.
 */
class CharacterFragment : Fragment() {

    var adapter: MyCharacterRecyclerViewAdapter = MyCharacterRecyclerViewAdapter()

    private val realmListener = object: RealmChangeListener<RealmResults<CharacterToRealm>> {
        override fun onChange(t: RealmResults<CharacterToRealm>) {
            adapter.notifyDataSetChanged()
        }

    }

    // TODO: Customize parameters
    private var columnCount = 1
//    var newFavorite:Boolean = false

    private var listener: OnListFragmentInteractionListener? = null

//    var update:Boolean = false

    override fun onResume() {
        super.onResume()
        // kopiare realm kod
        var config = RealmConfiguration.Builder().name("character.realm").build()
        var realm = Realm.getInstance(config)
        realm.where(CharacterToRealm::class.java).findAll().addChangeListener(realmListener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)
        //adapter = MyCharacterRecyclerViewAdapter()
        view.recyclerView_character.adapter = adapter
        view.recyclerView_character.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        view.search_character_button.setOnClickListener { _ ->
            RetrofitClientInstance.service.searchCharacters(view.EditText_search_character.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ wrapper ->
                    adapter.mValues = wrapper.data.results
                    adapter.notifyDataSetChanged()
                }
//            update=true
        }
//        if(update){
//            adapter.notifyDataSetChanged()
//        }


        return view
    }

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
        fun onListFragmentInteraction(item: MarvelCharacter)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, 1)
                }
            }

//        fun setnewFavorite1(newF: Boolean) {
//                CharacterFragment.setnewFavorite(newF)
//        }
    }
}
