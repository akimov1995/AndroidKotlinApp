package com.example.testapp.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.testapp.R
import com.example.testapp.ui.adapters.NewsAdapter
import com.example.testapp.mvp.model.Result
import com.example.testapp.mvp.presenter.MainPresenter
import com.example.testapp.mvp.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showNews(news: List<Result>) {
        recyclerView = findViewById(R.id.recycler_view)
        adapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter.setData(news)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showError(text: Int) {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}