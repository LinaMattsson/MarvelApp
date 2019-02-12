package org.androidcourse.marvel

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.character_row.*
import kotlinx.android.synthetic.main.fragment_allcomics_list.view.*
import kotlinx.android.synthetic.main.fragment_main_list.view.*


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [AllComicsFragment.OnListFragmentInteractionListener] interface.
 */
class AllComicsFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

   private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_allcomics_list, container, false)
        var adapter = MyAllComicsRecyclerViewAdapter()
        view.recyclerView_all_comics.adapter = adapter
        view.recyclerView_all_comics.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        RetrofitClientInstance.service.getAllComics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{wrapper ->
                adapter.mValues.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
            }
        view.recyclerView_all_comics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    RetrofitClientInstance.service.getAllComics(adapter.mValues.size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { wrapper ->
                            adapter.mValues.addAll(wrapper.data.results)
                            adapter.notifyDataSetChanged()
                        }
                    println(adapter.mValues.size.toString() + "AAAAAAAAAAAAAAAAAAA")
                }
            }
        })
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
        fun onListFragmentInteraction()
    }

    companion object {

        // TODO: Customize parameter argument names

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            AllComicsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
