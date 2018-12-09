package com.szenasi.test.data

import com.szenasi.test.data.model.GetItemsResponse
import io.reactivex.Single

class GetItemsDataSource(private val getItemsApi: GetItemsApi) {
    fun getItems(): Single<GetItemsResponse> {
        return getItemsApi.getItems()
    }
}