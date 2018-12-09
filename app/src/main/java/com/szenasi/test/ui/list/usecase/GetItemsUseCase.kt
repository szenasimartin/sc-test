package com.szenasi.test.ui.list.usecase

import com.szenasi.test.data.GetItemsDataSource
import com.szenasi.test.data.model.GetItemsResponse
import io.reactivex.Single
import javax.inject.Inject

class GetItemsUseCase @Inject internal constructor(private val getItemsDataSource: GetItemsDataSource) {

    fun execute(): Single<GetItemsResponse> {
        return getItemsDataSource.getItems()

    }
}
