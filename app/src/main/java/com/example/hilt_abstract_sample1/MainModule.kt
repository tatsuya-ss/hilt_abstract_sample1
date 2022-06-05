package com.example.hilt_abstract_sample1

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(ActivityComponent::class)
@Module
object MainModule {
    @Provides
    fun provideCountUseCase(
        countRepository: CountRepository,
    ): CountUseCase {
        return CountUseCase(countRepository)
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindCountRepository(countRepositoryImpl: CountRepositoryImpl): CountRepository
}

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideCoroutine(): Coroutine {
        return CoroutineImpl()
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
