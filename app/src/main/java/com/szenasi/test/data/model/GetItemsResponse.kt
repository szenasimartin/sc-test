package com.szenasi.test.data.model

import com.squareup.moshi.Json

data class GetItemsResponse(
    @field:Json(name = "Items") val items: List<Item>
)