package com.ahanafrifat.paybackcodingchallenge.di

import com.ahanafrifat.paybackcodingchallenge.data.repository.Repository
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.UseCases
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_hits.GetAllHitsUseCase
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_selected_hit.GetSelectedHitUseCases
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.search_hits.SearchHitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository:Repository):UseCases{
        return UseCases(
            getAllHitsUseCase = GetAllHitsUseCase(repository),
            searchHitsUseCase = SearchHitsUseCase(repository),
            getSelectedHitUseCases = GetSelectedHitUseCases(repository)
        )
    }
}