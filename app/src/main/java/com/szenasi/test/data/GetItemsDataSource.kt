package com.szenasi.test.data

import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.data.model.Item
import com.szenasi.test.data.model.ItemDetails
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.utils.API_KEY
import io.reactivex.Flowable
import io.reactivex.Single

class GetItemsDataSource(private val getItemsApi: GetItemsApi) {
    fun getItems(query: String): Single<GetItemsResponse> {
        return getItemsApi.getItems(API_KEY, query)
    }


    fun getItemDetails(response: GetItemsResponse): Flowable<ItemWithDetails> {
        return Flowable.fromIterable(response.items).flatMapSingle { item ->
            getItemsApi.getDetails(item.id, API_KEY).map { createItem(item, it) }
        }
    }

    private fun createItem(item: Item, itemDetails: ItemDetails): ItemWithDetails {
        return ItemWithDetails(item.id, item.name, item.posterPath ?: "", item.popularity, itemDetails.budget, itemDetails.releaseDate)
    }
}