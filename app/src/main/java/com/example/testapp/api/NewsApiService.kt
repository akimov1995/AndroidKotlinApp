package com.example.testapp.api

import com.example.testapp.mvp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("all/sports.json?")
    fun getNews(@Query("api-key") key: String, @Query("limit") limit: Int): Call<News>
}