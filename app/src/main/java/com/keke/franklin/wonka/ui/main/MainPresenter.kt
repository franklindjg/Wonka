package com.keke.franklin.wonka.ui.main

import android.content.Context
import com.keke.franklin.wonka.Wonka
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.data.network.model.Data
import com.keke.franklin.wonka.data.network.model.MResponse
import com.keke.franklin.wonka.ui.base.BasePresenter
import com.keke.franklin.wonka.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val context: Context, private var mainView: IMainView?) : BasePresenter(), IMainPresenter {

    override fun onResume() {
        mainView?.showProgress()
        if((context as Wonka).items != null) {
            mainView?.hideProgress()
            mainView?.notifyActivity((context).items!!.toList())
        } else {
            context.items = arrayListOf()
            getPage(1)
        }
    }

    override fun onDestroy() {
        // memory leak avoid
        mainView = null
    }

    override fun getPage(page: Int) {
        val callback = object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                mainView?.apply {
                    hideProgress()
                    showMessage(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val result = MResponse(response.body())
                onResultLoaded(result.data)
                val context = (context as Wonka)
                context.items?.addAll(result.data?.results!!)
                context.currentPage = page
            }
        }

        if (NetworkUtils.isNetworkConnected(context)) {
            (context as Wonka).getRestManager().getPage(page, callback)
        } else {
            mainView?.apply {
                hideProgress()
                showMessage(context.getString(R.string.no_internet))
            }
        }
    }

    override fun onResultLoaded(data: Data?) {
        mainView?.apply {
            hideProgress()
            data?.results?.let { notifyActivity(it) } ?: showMessage(context.getString(R.string.no_data))
        }
    }

    override fun onItemClicked(id: Int) {
        mainView?.launchActivity(id)
    }

}