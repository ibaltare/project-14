package com.keepcoding.navi.marvelapp.data.remote.response

import com.squareup.moshi.Json


data class CharacterDataWrapper (
    @Json(name = "data") val data: CharacterDataContainer
)

data class CharacterDataContainer (
    @Json(name = "results") val results: List<CharacterDTO>
)

data class CharacterDTO (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "resourceURI") val resourceURI: String
)

data class Thumbnail (
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)