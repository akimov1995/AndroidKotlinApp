package com.example.testapp.mvp.presenter

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.testapp.R
import com.example.testapp.application.TestApplication
import com.example.testapp.api.NewsApiService
import com.example.testapp.mvp.model.Result
import com.example.testapp.mvp.model.News
import com.example.testapp.mvp.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var retrofit: Retrofit

    private var resources: Resources

    init {
        TestApplication.graph.inject(this)
        resources = TestApplication.context.resources
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadNews()
    }

    private fun loadNews() {
        val service = retrofit.create(NewsApiService::class.java)
        val apiKey = resources.getString(R.string.api_key)
        val newsCount = resources.getInteger(R.integer.news_limit)
        val call = service.getNews(key = apiKey, limit = newsCount)

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.code() == 200) {
                    val response = response.body()!!
                    val news: List<Result> = response.results
                    viewState.showNews(news)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                viewState.showError(text = R.string.error)
            }
        })
    }
}