package com.keke.franklin.wonka.data.network

import com.keke.franklin.wonka.data.network.model.Data
import com.keke.franklin.wonka.data.network.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRest {

    /**
     * Método con notación get para obtener una lista de elementos del servidor
     */
    @GET("oompa-loompas")
    fun getPage(@Query("page") page: Int): Call<Data>

    /**
     * Método con notación get para obtener un elemento del servidor
     */
    @GET("oompa-loompas/{id}")
    fun getItem(@Path("id") id: Int): Call<Result>

}