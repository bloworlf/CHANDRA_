package io.drdroid.chandra.data.model.country


import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("currency")
    val currency: CurrencyModel?,
    @SerializedName("demonym")
    val demonym: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("language")
    val language: LanguageModel?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?
)