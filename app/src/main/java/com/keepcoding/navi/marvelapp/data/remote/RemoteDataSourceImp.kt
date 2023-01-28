package com.keepcoding.navi.marvelapp.data.remote

import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: MarvelApi): RemoteDataSource {

    override suspend fun getCharacters(offset: Int, limit: Int): Result<List<CharacterDTO>> {
        return runCatching { api.getCharacters(offset, limit).data.results }
    }

    override suspend fun getComics(
        characterId: Int,
        offset: Int,
        limit: Int
    ): Result<List<ComicDTO>> {
        return runCatching { api.getComicsByCharacterId(characterId, offset, limit).data.results }
    }
}