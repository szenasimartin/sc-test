package com.szenasi.test.ui.list.presenter

import android.util.Log
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.base.usecase.BaseUseCase
import com.szenasi.test.ui.base.presenter.BasePresenter
import com.szenasi.test.ui.list.view.ListContract
import com.szenasi.test.utils.BaseSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class ListPresenter<V : ListContract.ListView>(
    schedulerProvider: BaseSchedulerProvider, disposable: CompositeDisposable, private val getItemsUseCase: BaseUseCase<String, Single<GetItemsResponse>>, private val getAllItemDetailsUseCase: BaseUseCase<GetItemsResponse, Flowable<ItemWithDetails>>
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
                    getView()?.hideLoading()
                    getView()?.showError(err.toString())
                },{
                    getView()?.showAllCompleted()
                })
        )
    }
}

