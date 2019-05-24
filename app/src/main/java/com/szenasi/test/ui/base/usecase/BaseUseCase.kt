package com.szenasi.test.ui.base.usecase

interface BaseUseCase <V,S> {
    fun execute(input: V): S
}