package com.keepcoding.navi.marvelapp.data.mappers

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import javax.inject.Inject

class EntityMapper  @Inject constructor(){
    fun dtoMap(characterDTO: List<CharacterDTO>): List<CharacterEntity> {
        return characterDTO.map { CharacterEntity(
            it.id,
            it.name,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
            it.description,
            false
        ) }
    }
}