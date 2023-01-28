package com.keepcoding.navi.marvelapp.di

import com.keepcoding.navi.marvelapp.data.HomeRepositoryImp
import com.keepcoding.navi.marvelapp.data.local.LocalDataSource
import com.keepcoding.navi.marvelapp.data.local.LocalDataSourceImp
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSourceImp
import com.keepcoding.navi.marvelapp.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource

    @Binds
    abstract fun bindHomeRepository(homeRepositoryImp: HomeRepositoryImp): HomeRepository
}