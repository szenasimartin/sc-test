package com.szenasi.test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.szenasi.test.data.model.GetItemsResponse
import com.szenasi.test.data.model.Item
import com.szenasi.test.data.model.ItemWithDetails
import com.szenasi.test.ui.base.usecase.BaseUseCase
import com.szenasi.test.ui.list.presenter.ListPresenter
import com.szenasi.test.ui.list.view.ListContract
import com.szenasi.test.utils.TrampolineSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class ListPresenterTest {
    private var view : ListContract.ListView = mock()
    private var getItemsUseCase : BaseUseCase<String, Single<GetItemsResponse>> = mock()
    private var getAllItemsUseCase : BaseUseCase<GetItemsResponse, Flowable<ItemWithDetails>> = mock()
    private var schedulerProvider = TrampolineSchedulerProvider()
    private val presenter = ListPresenter<ListContract.ListView>(schedulerProvider, CompositeDisposable(), getItemsUseCase, getAllItemsUseCase)

    @Before
    fun setUp() {
        presenter.onAttach(view)
    }

    @Test
    fun shouldShowItems() {
        //given
        whenever(getItemsUseCase.execute("Star Wars")).thenReturn(Single.just(GetItemsResponse(listOf(Item(1,"Star Wars","",10.0)))))
        whenever(getAllItemsUseCase.execute(GetItemsResponse(listOf(Item(1,"Star Wars","",10.0)))))
            .thenReturn(Flowable.just(ItemWithDetails(1,"Star Wars","",10.0,2.0,"2010")))

        //when
        presenter.getItems("Star Wars")

        //then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showItems(ItemWithDetails(1,"Star Wars","",10.0,2.0,"2010"))
        verify(view).showAllCompleted()
    }

    @Test
    fun shouldShowItemError() {
        //given
        whenever(getItemsUseCase.execute("Star Wars")).thenReturn(Single.error(Exception("error")))

        //when
        presenter.getItems("Star Wars")

        //then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError("java.lang.Exception: error")
    }

    @Test
    fun shouldShowAllItemError() {
        //given
        whenever(getItemsUseCase.execute("Star Wars")).thenReturn(Single.just(GetItemsResponse(listOf(Item(1,"Star Wars","",10.0)))))
        whenever(getAllItemsUseCase.execute(GetItemsResponse(listOf(Item(1,"Star Wars","",10.0)))))
            .thenReturn(Flowable.error(Exception("error")))

        //when
        presenter.getItems("Star Wars")

        //then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError("java.lang.Exception: error")
    }
}

