package com.example.testapp.mvp.view

import com.arellomobile.mvp.MvpView
import com.example.testapp.mvp.model.Result

interface MainView: MvpView {
    fun showNews(news: List<Result>)
    fun showError(text: Int)
}