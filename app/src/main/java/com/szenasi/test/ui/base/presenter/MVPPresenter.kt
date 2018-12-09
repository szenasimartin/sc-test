package com.szenasi.test.ui.base.presenter

import com.szenasi.test.ui.base.view.MVPView


interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}