package com.keepcoding.navi.marvelapp.data.remote

import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDataWrapper
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDataWrapper
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): CharacterDataWrapper

    @GET("characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int ): ComicDataWrapper
}