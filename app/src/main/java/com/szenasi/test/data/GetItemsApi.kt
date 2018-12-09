package com.szenasi.test.data

import com.szenasi.test.data.model.GetItemsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GetItemsApi {
    @GET("/getitems")
    fun getItems(): Single<GetItemsResponse>
}