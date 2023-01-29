package com.keepcoding.navi.marvelapp.utils

import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDTO
import com.keepcoding.navi.marvelapp.data.remote.response.Thumbnail
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.domain.Hero

object FakeData {
    fun getFakeHeroes() = listOf<Hero>(
        Hero(id = 1, name = "Spiderman","https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",true),
        Hero(id = 2, name = "hulk","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false),
        Hero(id = 3, name = "thor","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",true),
        Hero(id = 4, name = "superman","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false),
        Hero(id = 5, name = "Goku","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",true),
    )

    fun getFakeCharacters() = listOf<CharacterEntity>(
        CharacterEntity(1, "Spiderman", "https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries", true),
                CharacterEntity(1, "Batman",
        "https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries", true)
    )

    fun getCharacter() = CharacterEntity(
        1,
        "Spiderman",
        "https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
        true)

    fun getFakeComics() = listOf<Comic>(
        Comic(id = 1, name = "Spiderman","https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg"),
        Comic(id = 2, name = "hulk","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg"),
        Comic(id = 3, name = "thor","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg"),
        Comic(id = 4, name = "superman","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg"),
        Comic(id = 5, name = "Goku","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg"),
    )

    fun getCharacterDTOList(): List<CharacterDTO> {
        return (0 until 5).map {
            CharacterDTO(
                it,
                "Name $it",
                "Photo $it",
                Thumbnail("path:$it","ext:$it"),
                "Uri $it"
            )
        }
    }

    fun getComicDTOList(): List<ComicDTO> {
        return (0 until 5).map {
            ComicDTO(
                it,
                "Title $it",
                "Variant $it",
                "Description $it",
                "Uri $it",
                Thumbnail("path:$it","ext:$it"),
                listOf<Thumbnail>(Thumbnail("path:$it","ext:$it"))
            )
        }
    }
}