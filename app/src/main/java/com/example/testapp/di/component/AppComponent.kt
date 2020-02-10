package com.example.testapp.di.component

import com.example.testapp.di.module.RetrofitModule
import dagger.Component
import com.example.testapp.mvp.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
}