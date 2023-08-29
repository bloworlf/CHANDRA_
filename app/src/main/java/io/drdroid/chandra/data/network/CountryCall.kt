package io.drdroid.chandra.data.network

import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.utils.Common
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryCall {

    @GET(Common.API.ALL_COUNTRIES_ENDPOINT)
    suspend fun getCountries(): Response<List<CountryModel>>

    @GET(Common.API.ALL_COUNTRIES_ENDPOINT)
    suspend fun getCountryByName(
        @Path("name") name: String
    ): Response<CountryModel>
}