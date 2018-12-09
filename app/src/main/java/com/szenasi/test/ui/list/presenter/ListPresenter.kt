package com.szenasi.test.ui.list.presenter

import android.util.Log
import com.szenasi.test.ui.base.presenter.BasePresenter
import com.szenasi.test.ui.list.usecase.GetItemsUseCase
import com.szenasi.test.ui.list.view.ListContract
import com.szenasi.test.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ListPresenter<V : ListContract.ListView>(
    schedulerProvider: SchedulerProvider, disposable: CompositeDisposable, private val getItemsUseCase: GetItemsUseCase
) :
    BasePresenter<V>(schedulerProvider, disposable), ListContract.ListPresenter {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getItems()
    }

    private fun getItems() {
        getView()?.showLoading()
        compositeDisposable.add(
            getItemsUseCase.execute()
                .compose(schedulerProvider.ioToMainSingleScheduler())
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
