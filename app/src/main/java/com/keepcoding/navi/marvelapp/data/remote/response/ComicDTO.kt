package com.keepcoding.navi.marvelapp.data.remote.response

import com.squareup.moshi.Json


data class ComicDataWrapper (
    @Json(name = "data") val data: ComicDataContainer
)

data class ComicDataContainer (
    @Json(name = "results") val results: List<ComicDTO>
)

data class ComicDTO (
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "variantDescription") val variantDescription: String,
    @Json(name = "description") val description: String? = null,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "images") val images: List<Thumbnail>,
)

