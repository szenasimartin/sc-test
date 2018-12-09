package com.szenasi.test.data

import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.data.model.ItemDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetItemsApi {
    @GET("/3/search/movie")
    fun getItems(@Query("api_key") key: String, @Query("query") query: String): Single<GetItemsResponse>

    @GET("/3/movie/{movie_id}")
    fun getDetails(@Path("movie_id") id: Int, @Query("api_key") key: String): Single<ItemDetails>
}