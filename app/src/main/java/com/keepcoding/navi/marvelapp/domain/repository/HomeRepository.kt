package com.keepcoding.navi.marvelapp.domain.repository

import com.keepcoding.navi.marvelapp.domain.Hero
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getCharacters(): Flow<List<Hero>>
    suspend fun setFavorite(id: Int)
    suspend fun deleteLocalData()
}