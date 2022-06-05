package com.example.hilt_abstract_sample1

import android.app.Activity
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Qualifier
annotation class Main

@Qualifier
annotation class Sub

@InstallIn(ActivityComponent::class)
@Module
object MainModule {
    @Provides
    fun provideMainView(activity: Activity): PresenterContract.View {
        return activity as PresenterContract.View
    }

    @Main
    @Provides
    fun provideMainPresenter(
        view: PresenterContract.View
    ): PresenterContract.Presenter {
        return MainPresenter(view)
    }

    @Sub
    @Provides
    fun provideSubPresenter(
        view: PresenterContract.View
    ): PresenterContract.Presenter {
        return SubPresenter(view)
    }
}

@InstallIn(FragmentComponent::class)
@Module
object FragmentModule {
    @Provides
    fun provideCount(
    ): Count {
        return CountImpl()
    }
}