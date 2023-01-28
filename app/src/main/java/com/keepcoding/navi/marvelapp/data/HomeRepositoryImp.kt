package com.keepcoding.navi.marvelapp.data

import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.domain.Hero
import com.keepcoding.navi.marvelapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): HomeRepository {

    override suspend fun getCharacters(): Flow<List<Hero>> {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite(like: Boolean) {
        TODO("Not yet implemented")
    }

    /*override suspend fun getRemoteCharacters(offset: Int, limit: Int): Flow<List<Hero>> {
        val result = remoteDataSource.getCharacters(20,20)
    }*/

    override suspend fun deleteLocalData() {
    }
}