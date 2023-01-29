package com.keepcoding.navi.marvelapp.data

import com.keepcoding.navi.marvelapp.data.local.LocalDataSource
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.data.mappers.PresentationMapper
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val presentationMapper: PresentationMapper
): DetailRepository {

    override suspend fun getCharacter(id: Int): CharacterEntity {
        return localDataSource.getCharacterById(id)
    }

    override suspend fun getComics(id: Int): Flow<List<Comic>> {
        val result = remoteDataSource.getComics(id,0,20)
        return when{
            result.isSuccess -> {
                flow { emit(result.getOrThrow()) }.map { presentationMapper.dtoComicMap(it) }
            }
            else -> {
                throw Exception("Error al obtener los datos")
            }
        }
    }
}