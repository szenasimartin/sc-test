package com.szenasi.test.data.model

import com.squareup.moshi.Json


data class ItemDetails(
    @field:Json(name = "budget") val budget: Double, @field:Json(name = "release_date") val releaseDate: String
)