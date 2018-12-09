package com.szenasi.test.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @field:Json(name = "Id") val id: Int, @field:Json(name = "Name") val name: String
) : Parcelable