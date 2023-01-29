package com.keepcoding.navi.marvelapp.fakes

import com.keepcoding.navi.marvelapp.data.local.LocalDataSource
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.utils.FakeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource(private val shared: Boolean = false): LocalDataSource {

    private val sharedFlow = MutableSharedFlow<List<CharacterEntity>>()

    suspend fun emit(list: List<CharacterEntity>){
        sharedFlow.emit(list)
    }

    override suspend fun size(): Int = FakeData.getFakeCharacters().size

    override suspend fun saveCharacters(characters: List<CharacterEntity>) { }

    override fun getCharacters(): Flow<List<CharacterEntity>> {
        return  if (shared){
            sharedFlow
        } else flow { FakeData.getFakeCharacters() }
    }

    override suspend fun deleteAll() { }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
        return FakeData.getCharacter()
    }

    override suspend fun updateCharacter(characterEntity: CharacterEntity) { }
}