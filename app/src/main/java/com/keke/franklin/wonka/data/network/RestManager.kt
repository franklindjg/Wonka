package com.keke.franklin.wonka.data.network

import com.keke.franklin.wonka.data.network.model.Data
import com.keke.franklin.wonka.data.network.model.Result
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestManager {

    private val service : IRest

    companion object {
        const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"
    }

    // Setup retrofit
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(IRest::class.java)
    }

    fun getPage(page: Int, callback: Callback<Data>) {
        service.getPage(page).enqueue(callback)
    }

    fun getItem(id: Int, callback: Callback<Result>) {
        service.getItem(id).enqueue(callback)
    }
}