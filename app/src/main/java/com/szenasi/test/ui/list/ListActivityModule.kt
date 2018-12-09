package com.szenasi.test.ui.list

import com.szenasi.test.ui.list.presenter.ListPresenter
import com.szenasi.test.ui.list.usecase.GetItemsUseCase
import com.szenasi.test.ui.list.view.ListContract
import com.szenasi.test.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ListActivityModule {

    @Provides
    internal fun provideListPresenter(
        schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable,
        getItemsUseCase: GetItemsUseCase
    )
            : ListPresenter<ListContract.ListView> = ListPresenter(
        schedulerProvider, compositeDisposable, getItemsUseCase
    )

}