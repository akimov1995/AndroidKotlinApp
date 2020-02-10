package com.example.testapp.application

import android.app.Application
import android.content.Context
import com.example.testapp.di.module.RetrofitModule
import com.example.testapp.di.component.AppComponent
import com.example.testapp.di.component.DaggerAppComponent

class TestApplication : Application() {
    companion object {
        lateinit var graph: AppComponent
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        graph = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build()
    }
}