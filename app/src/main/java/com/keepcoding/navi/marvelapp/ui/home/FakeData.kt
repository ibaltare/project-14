package com.keepcoding.navi.marvelapp.ui.home

import com.keepcoding.navi.marvelapp.domain.Hero

object FakeData {
    fun getFakeHeroes() = listOf<Hero>(
        Hero(id = 1, name = "Spiderman","https://upload.wikimedia.org/wikipedia/commons/5/52/Spider-Man.jpg",true),
        Hero(id = 1, name = "hulk","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false),
        Hero(id = 1, name = "thor","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",true),
        Hero(id = 1, name = "superman","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",false),
        Hero(id = 1, name = "Goku","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Hulk_%282540708438%29.jpg/480px-Hulk_%282540708438%29.jpg",true),
    )
}