package com.keke.franklin.wonka.ui.detail

import com.keke.franklin.wonka.data.network.model.Result
import com.keke.franklin.wonka.ui.base.IBaseView

interface IDetailView : IBaseView {
    fun notifyActivity(result: Result)
}