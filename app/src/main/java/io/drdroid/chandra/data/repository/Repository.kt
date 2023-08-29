package io.drdroid.chandra.data.repository

import io.drdroid.chandra.data.model.country.CountryModel
import retrofit2.Response

interface Repository {

    suspend fun getCountries(): Response<List<CountryModel>>
}