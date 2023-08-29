package io.drdroid.chandra.data.model.country


import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)