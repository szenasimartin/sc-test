package com.szenasi.test.data.model

import com.squareup.moshi.Json

data class GetItemsResponse(
    @field:Json(name = "results") val items: List<Item>
)