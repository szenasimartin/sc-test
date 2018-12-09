package com.szenasi.test.ui.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.szenasi.test.R
import com.szenasi.test.data.model.Item
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.base.view.BaseActivity
import com.szenasi.test.ui.detail.presenter.DetailsPresenter
import javax.inject.Inject


private const val INTENT_ITEM_ID = "item_id"


fun Context.DetailIntent(item: ItemWithDetails): Intent {
    return Intent(this, DetailsActivity::class.java).apply {
        putExtra(INTENT_ITEM_ID, item)
    }
}

class DetailsActivity : BaseActivity(), DetailsContract.DetailsView {

    @Inject
    internal lateinit var presenter: DetailsPresenter<DetailsContract.DetailsView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        presenter.onAttach(this)
        val item: ItemWithDetails = intent.getParcelableExtra(INTENT_ITEM_ID)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


}