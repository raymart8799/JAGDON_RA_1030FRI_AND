package com.jagdon.newsapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

@Suppress("DEPRECATION")
class HeadlineListFragment : ListFragment() {

    private var listener: OnHeadlineClickListener? = null

    interface OnHeadlineClickListener {
        fun onHeadlineSelected(position: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnHeadlineClickListener
            ?: throw ClassCastException("$context must implement OnHeadlineClickListener")
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Sample headlines
        val headlines = arrayOf("News 1", "News 2", "News 3", "News 4")

        // Adapter for the list
        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, headlines)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        listener?.onHeadlineSelected(position)
    }
}
