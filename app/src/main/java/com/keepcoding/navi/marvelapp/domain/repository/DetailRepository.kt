package com.keepcoding.navi.marvelapp.domain.repository

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.domain.Comic
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getCharacter(id: Int): CharacterEntity
    suspend fun getComics(id: Int): Flow<List<Comic>>
}