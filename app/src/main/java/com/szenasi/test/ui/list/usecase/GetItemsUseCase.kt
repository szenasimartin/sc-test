package com.szenasi.test.ui.list.usecase

import com.szenasi.test.data.GetItemsDataSource
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.ui.base.usecase.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetItemsUseCase @Inject internal constructor(private val getItemsDataSource: GetItemsDataSource):
    BaseUseCase<String, Single<GetItemsResponse>> {

    override fun execute(query: String): Single<GetItemsResponse> {
        return getItemsDataSource.getItems(query)

    }
}
