package com.keepcoding.navi.marvelapp.data.remote

import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDTO

interface RemoteDataSource {
    suspend fun getCharacters(offset: Int, limit: Int):Result<List<CharacterDTO>>
    suspend fun getComics(characterId: Int, offset: Int, limit: Int):Result<List<ComicDTO>>
}