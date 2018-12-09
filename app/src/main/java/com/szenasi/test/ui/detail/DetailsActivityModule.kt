package com.szenasi.test.ui.detail

import com.szenasi.test.ui.detail.presenter.DetailsPresenter
import com.szenasi.test.ui.detail.view.DetailsContract
import com.szenasi.test.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DetailsActivityModule {

    @Provides
    internal fun provideDetailPresenter(
        schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable
    )
            : DetailsPresenter<DetailsContract.DetailsView> = DetailsPresenter(
        schedulerProvider, compositeDisposable
    )

}