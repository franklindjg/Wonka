package com.keke.franklin.wonka

import android.app.Application
import android.os.Parcelable
import com.keke.franklin.wonka.data.network.RestManager
import com.keke.franklin.wonka.data.network.model.Result

class Wonka : Application() {
    var items : MutableList<Result>? = null
    var currentPage : Int? = null
    var selectedItemFilter : Int = 2
    var recyclerState : Parcelable? = null
    private var restManager :RestManager? = null

    override fun onCreate() {
        super.onCreate()
        restManager = RestManager()
    }

    fun getRestManager() : RestManager {
        return restManager as RestManager
    }
}