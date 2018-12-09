package com.szenasi.test.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemWithDetails(
    val id: Int, val name: String, val posterPath: String, val popularity: String, val budget: Double
) : Parcelable