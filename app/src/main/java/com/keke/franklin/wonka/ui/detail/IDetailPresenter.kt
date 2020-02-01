package com.keke.franklin.wonka.ui.detail

import com.keke.franklin.wonka.data.network.model.Result

interface IDetailPresenter {
    fun getById(id: Int)
    fun onResultLoaded(result: Result?)
}