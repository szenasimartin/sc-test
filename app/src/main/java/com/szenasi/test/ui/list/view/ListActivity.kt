package com.szenasi.test.ui.list.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar
import com.szenasi.test.R
import com.szenasi.test.data.model.ItemWithDetails
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
        searchButton.setOnClickListener {
            adapter.clear()
            presenter.getItems(searchEditText.text.toString())
            hideKeyboard()
        }

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
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        searchButton.background = circularProgressDrawable
        searchButton.isEnabled = false
    }

    override fun hideLoading() {
        shimmerLayout.visibility = View.GONE
    }

    override fun showItems(items: ItemWithDetails) {
        adapter.addItem(items)
    }

    override fun showError(s: String) {
        val snackbar = Snackbar.make(
            coordinatorLayout, s,
            Snackbar.LENGTH_LONG
        )
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.RED)
        snackbar.show()
    }

    override fun showAllCompleted() {
        searchButton.background = null
        searchButton.isEnabled = true
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
