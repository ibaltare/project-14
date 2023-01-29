package com.keepcoding.navi.marvelapp.data.mappers

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDTO
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.domain.Hero
import javax.inject.Inject

class PresentationMapper @Inject constructor(){

    fun dtoMap(characterDTO: List<CharacterDTO>): List<Hero> {
        return characterDTO.map { Hero(
            it.id,
            it.name,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
        false
        ) }
    }

    fun entityMap(characterEntity: List<CharacterEntity>): List<Hero> {
        return characterEntity.map { Hero(it.id,it.name,it.thumbnail,it.favorite) }
    }

    fun dtoComicMap(comicDTO: List<ComicDTO>): List<Comic> {
        return comicDTO.map { Comic(
            it.id,
            it.title,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
        ) }
    }
}