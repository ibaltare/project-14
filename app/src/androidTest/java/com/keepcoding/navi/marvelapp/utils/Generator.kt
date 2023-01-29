package com.keepcoding.navi.marvelapp.utils

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.domain.Hero

object FakeData {
    fun getFakeHeroes() = listOf<Hero>(
        Hero(id = 1, name = "Spiderman","https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",true),
        Hero(id = 2, name = "hulk","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false),
        Hero(id = 3, name = "thor","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",true),
        Hero(id = 4, name = "superman","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false)
    )

    fun getCharacter() = CharacterEntity(
        1,
        "Spiderman",
        "https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
        true)

    fun getFakeComics() = listOf<Comic>(
        Comic(id = 1, name = "ComicName","https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg")
    )
}