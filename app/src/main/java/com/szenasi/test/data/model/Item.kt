package com.szenasi.test.data.model

import com.squareup.moshi.Json


data class Item(
    @field:Json(name = "id") val id: Int, @field:Json(name = "title") val name: String, @field:Json(name = "poster_path") val posterPath: String,
    @field:Json(name = "popularity") val popularity: Double
)