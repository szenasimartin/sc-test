package com.szenasi.test.ui.list.view

import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.ui.base.view.MVPView

interface ListContract {
    interface ListPresenter

    interface ListView : MVPView {
        fun showLoading()
        fun hideLoading()
        fun showItems(items: GetItemsResponse)
        fun showError(s: String)
    }
}
