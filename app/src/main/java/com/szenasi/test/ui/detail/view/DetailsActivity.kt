package com.szenasi.test.ui.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.base.view.BaseActivity
import com.szenasi.test.ui.detail.presenter.DetailsPresenter
import com.szenasi.test.utils.load
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject
import com.szenasi.test.R


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
        detailsPosterImage.load(item.posterPath)
        detailsToolbar.title = item.name
        detailsitemBudget.text = getString(R.string.budget).format(item.budget / 1000000)
        detailsPopularity.text = getString(R.string.popularity).format(item.popularity)
        detailsReleaseDate.text = getString(R.string.release_date, item.releaseDate)
        setToolbar()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setToolbar() {
        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}