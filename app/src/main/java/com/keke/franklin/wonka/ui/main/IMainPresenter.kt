package com.keke.franklin.wonka.ui.main

import com.keke.franklin.wonka.data.network.model.Data

interface IMainPresenter {
    fun getPage(page: Int)
    fun onResultLoaded(data: Data?)
    fun onItemClicked(id: Int)
}