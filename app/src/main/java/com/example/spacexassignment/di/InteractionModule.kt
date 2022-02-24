package com.example.spacexassignment.di

import com.example.spacexassignment.model.network.api.SpaceXApi
import com.example.spacexassignment.model.repositories.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractionModule {


    @ViewModelScoped
    @Provides
    fun getHomeRepo(
        spaceXApi: SpaceXApi
    ): HomeRepo {
        return HomeRepo(spaceXApi)
    }

}