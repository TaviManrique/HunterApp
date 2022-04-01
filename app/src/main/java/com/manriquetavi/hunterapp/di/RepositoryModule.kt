package com.manriquetavi.hunterapp.di

import android.content.Context
import com.manriquetavi.hunterapp.data.repository.DataStoreOperationsImpl
import com.manriquetavi.hunterapp.data.repository.Repository
import com.manriquetavi.hunterapp.domain.repository.DataStoreOperations
import com.manriquetavi.hunterapp.domain.use_cases.UseCases
import com.manriquetavi.hunterapp.domain.use_cases.get_all_hunters.GetAllHuntersUseCase
import com.manriquetavi.hunterapp.domain.use_cases.get_selected_hunter.GetSelectedHunterUseCase
import com.manriquetavi.hunterapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.hunterapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.hunterapp.domain.use_cases.search_hunters.SearchHuntersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHuntersUseCase = GetAllHuntersUseCase(repository),
            searchHuntersUseCase = SearchHuntersUseCase(repository),
            getSelectedHunterUseCase = GetSelectedHunterUseCase(repository)
        )
    }
}