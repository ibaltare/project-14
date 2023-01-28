package com.keepcoding.navi.marvelapp.data.local

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun size(): Int
    suspend fun saveCharacters(characters: List<CharacterEntity>)
    fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun deleteAll()
    suspend fun getCharacterById(id: Int): CharacterEntity
}