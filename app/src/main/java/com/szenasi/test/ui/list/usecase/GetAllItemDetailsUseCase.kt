package com.szenasi.test.ui.list.usecase

import com.szenasi.test.data.GetItemsDataSource
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.data.model.ItemWithDetails
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllItemDetailsUseCase @Inject internal constructor(private val getItemsDataSource: GetItemsDataSource) {

    fun execute(response: GetItemsResponse): Flowable<ItemWithDetails> {
        return getItemsDataSource.getItemDetails(response)

    }
}