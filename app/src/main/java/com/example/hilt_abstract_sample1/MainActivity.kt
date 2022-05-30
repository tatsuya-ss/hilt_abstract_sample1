package com.example.hilt_abstract_sample1

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application()

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PresenterContract.View {
    @Main
    @Inject lateinit var presenter: PresenterContract.Presenter

    @Sub
    @Inject lateinit var subPresenter: PresenterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getName()
        subPresenter.getName()
    }

    override fun setName(name: String) {
        Log.d("Tatsuya🐲", "onCreate: ${name}")
    }
}

interface PresenterContract {
    interface View {
        fun setName(name: String)
    }

    interface Presenter {
        fun getName()
    }
}

abstract class BasePresenter() : PresenterContract.Presenter {
}

class MainPresenter @Inject constructor(
    val view: PresenterContract.View
) : BasePresenter() {
    override fun getName() {
        view.setName("Tatsuya")
    }
}

class SubPresenter @Inject constructor(
    val view: PresenterContract.View
) : BasePresenter() {
    override fun getName() {
        view.setName("SubTatsuya")
    }
}
