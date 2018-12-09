package com.szenasi.test.ui.list.view

import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.base.view.MVPView

interface ListContract {
    interface ListPresenter {
        fun getItems(query: String)
    }

    interface ListView : MVPView {
        fun showLoading()
        fun hideLoading()
        fun showItems(items: ItemWithDetails)
        fun showError(s: String)
        fun showAllCompleted()
    }
}
