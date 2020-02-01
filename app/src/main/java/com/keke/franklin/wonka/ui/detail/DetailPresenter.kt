package com.keke.franklin.wonka.ui.detail

import android.content.Context
import com.keke.franklin.wonka.Wonka
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.data.network.model.Result
import com.keke.franklin.wonka.ui.base.BasePresenter
import com.keke.franklin.wonka.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val context: Context, private var detailView: IDetailView?, private val id: Int) : BasePresenter(), IDetailPresenter{

    override fun onResume() {
        detailView?.showProgress()
        getById(id)
    }

    override fun onDestroy() {
        // memory leak avoid
        detailView = null
    }

    override fun getById(id: Int) {
        val callback = object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                detailView?.apply {
                    hideProgress()
                    showMessage(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                onResultLoaded(response.body())
            }
        }

        if (NetworkUtils.isNetworkConnected(context)) {
            (context as Wonka).getRestManager().getItem(id, callback)
        } else {
            detailView?.apply {
                hideProgress()
                showMessage(context.getString(R.string.no_internet))
            }
        }
    }

    override fun onResultLoaded(result: Result?) {
        detailView?.hideProgress()
        if (result != null) detailView?.notifyActivity(result)
    }
}