package com.keke.franklin.wonka.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Generate with http://www.jsonschema2pojo.org and converted to Kotlin
data class MResponse(val data:Data?)

data class Data (
    @SerializedName("current")
    @Expose
    val current: Int?,
    @SerializedName("total")
    @Expose
    val total: Int?,
    @SerializedName("results")
    @Expose
    val results: List<Result>?
)

data class Favorite (
    @SerializedName("color")
    @Expose
    val color: String?,
    @SerializedName("food")
    @Expose
    val food: String?
)

data class Result (
    @SerializedName("first_name")
    @Expose
    val firstName: String?,

    @SerializedName("last_name")
    @Expose
    val lastName: String?,

    @SerializedName("favorite")
    @Expose
    val favorite: Favorite?,

    @SerializedName("gender")
    @Expose
    val gender: String?,

    @SerializedName("image")
    @Expose
    val image: String?,

    @SerializedName("profession")
    @Expose
    val profession: String?,

    @SerializedName("age")
    @Expose
    val age: Int?,

    @SerializedName("country")
    @Expose
    val country: String?,

    @SerializedName("id")
    @Expose
    val id: Int?
)