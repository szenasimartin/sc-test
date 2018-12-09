package com.szenasi.test.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class ItemDetails(
    @field:Json(name = "budget") val budget: Double
)