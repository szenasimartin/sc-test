package com.szenasi.test.ui.list.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.szenasi.test.R
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.ui.base.view.BaseActivity
import com.szenasi.test.ui.list.presenter.ListPresenter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.placeholder.*
import javax.inject.Inject

class ListActivity : BaseActivity(), ListContract.ListView {
    @Inject
    internal lateinit var presenter: ListPresenter<ListContract.ListView>
    private val adapter = SearchResultAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setRecycleView()
        presenter.onAttach(this)
    }

    private fun setRecycleView() {
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun showLoading() {
        shimmerLayout.startShimmerAnimation()
    }

    override fun hideLoading() {
        shimmerLayout.visibility = View.GONE
    }

    override fun showItems(items: GetItemsResponse) {
        adapter.addItem(items.items)
    }

    override fun showError(s: String) {
    }
}
