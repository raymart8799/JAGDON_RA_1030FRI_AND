package com.jagdon.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment : Fragment() {

    private var newsText: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_content, container, false)
        newsText = view.findViewById(R.id.news_content_text)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // If there's news passed in as arguments, display it
        val news = arguments?.getString("news")
        newsText?.text = news
    }

    // This method is used in dual-pane mode to update the content dynamically
    fun updateContent(news: String) {
        newsText?.text = news
    }
}
