package com.keke.franklin.wonka.ui.main

import com.keke.franklin.wonka.data.network.model.Result
import com.keke.franklin.wonka.ui.base.IBaseView

interface IMainView : IBaseView {
    fun setRecyclerView()
    fun launchActivity(id: Int)
    fun notifyActivity(list: List<Result>)
}