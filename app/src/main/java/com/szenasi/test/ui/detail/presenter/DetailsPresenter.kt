package com.szenasi.test.ui.detail.presenter

import com.szenasi.test.ui.base.presenter.BasePresenter
import com.szenasi.test.ui.detail.view.DetailsContract
import com.szenasi.test.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class DetailsPresenter<V : DetailsContract.DetailsView>(
    schedulerProvider: SchedulerProvider, disposable: CompositeDisposable
) :
    BasePresenter<V>(schedulerProvider, disposable), DetailsContract.DetailsPresenter