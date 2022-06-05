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
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var countUseCase: CountUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Tatsuya\uD83D\uDC32", "onCreate: ${countUseCase.plus()}")

        supportFragmentManager.beginTransaction().apply {
            add(R.id.clMain, MainFragment())
            commit()
        }
    }
}

class CountUseCase @Inject constructor(
    private val countRepository: CountRepository,
) {
    fun plus(): Int {
        return countRepository.plus()
    }
}

interface CountRepository {
    fun plus(): Int
}

class CountRepositoryImpl @Inject constructor(
    private val coroutine: Coroutine,
) : CountRepository {
    private var count = coroutine.fetch()
    override fun plus(): Int {
        count++
        return count
    }
}

interface Coroutine {
    fun fetch(): Int
}

class CoroutineImpl : Coroutine {
    override fun fetch(): Int { return 100 }
}