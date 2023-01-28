package com.keepcoding.navi.marvelapp.data

import com.keepcoding.navi.marvelapp.data.local.LocalDataSource
import com.keepcoding.navi.marvelapp.data.mappers.EntityMapper
import com.keepcoding.navi.marvelapp.data.mappers.PresentationMapper
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.domain.Hero
import com.keepcoding.navi.marvelapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val entityMapper: EntityMapper,
    private val presentationMapper: PresentationMapper

): HomeRepository {

    override suspend fun getCharacters(): Flow<List<Hero>> {
        if (localDataSource.size() < 1){
            val result = remoteDataSource.getCharacters(20,20)
            return when{
                result.isSuccess -> {
                    localDataSource.saveCharacters(entityMapper.dtoMap(result.getOrThrow()))
                    flow { emit(result.getOrThrow()) }.map { presentationMapper.dtoMap(it) }
                }
                else -> {
                    throw Exception("Error al obtener los datos")
                }
            }
        }else{
            return localDataSource.getCharacters().map { presentationMapper.entityMap(it) }
        }
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