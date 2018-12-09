package com.szenasi.test.di.builder

import com.szenasi.test.ui.detail.DetailsActivityModule
import com.szenasi.test.ui.detail.view.DetailsActivity
import com.szenasi.test.ui.list.ListActivityModule
import com.szenasi.test.ui.list.view.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ListActivityModule::class)])
    abstract fun bindListActivity(): ListActivity

    @ContributesAndroidInjector(modules = [(DetailsActivityModule::class)])
    abstract fun bindDetailsActivity(): DetailsActivity

}