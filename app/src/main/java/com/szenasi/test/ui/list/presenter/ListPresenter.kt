package com.szenasi.test.ui.list.presenter

import android.util.Log
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.ui.base.presenter.BasePresenter
import com.szenasi.test.ui.list.usecase.GetAllItemDetailsUseCase
import com.szenasi.test.ui.list.usecase.GetItemsUseCase
import com.szenasi.test.ui.list.view.ListContract
import com.szenasi.test.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ListPresenter<V : ListContract.ListView>(
    schedulerProvider: SchedulerProvider, disposable: CompositeDisposable, private val getItemsUseCase: GetItemsUseCase, private val getAllItemDetailsUseCase: GetAllItemDetailsUseCase
) :
    BasePresenter<V>(schedulerProvider, disposable), ListContract.ListPresenter {
    override fun getItems(query: String) {
        getView()?.showLoading()
        compositeDisposable.add(
            getItemsUseCase.execute(query)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({
                    getAllItemDetails(it)
                }, { err ->
                    Log.e("Presenter", err.toString())
                    getView()?.hideLoading()
                    getView()?.showError(err.toString())
                })
        )
    }

    private fun getAllItemDetails(response: GetItemsResponse) {
        compositeDisposable.add(
            getAllItemDetailsUseCase.execute(response)
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    getView()?.hideLoading()
                    getView()?.showItems(it)
                }, { err ->
                    Log.e("Presenter", err.toString())
                    getView()?.hideLoading()
                    getView()?.showError(err.toString())
                })
        )
    }
}

