package com.keepcoding.navi.marvelapp.data.local

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: CharacterDAO): LocalDataSource {

    override suspend fun size(): Int = dao.characterCount()

    override suspend fun saveCharacters(characters: List<CharacterEntity>) {
        dao.insertAll(characters)
    }

    override fun getCharacters(): Flow<List<CharacterEntity>> = dao.getAllCharacters()

    override suspend fun deleteAll() {
        dao.deleteCharacters()
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
        return dao.getCharacterById(id)
    }

    override suspend fun updateCharacter(characterEntity: CharacterEntity) {
        dao.updateCharacter(characterEntity)
    }
}