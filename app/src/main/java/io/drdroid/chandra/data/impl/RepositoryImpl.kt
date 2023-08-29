package io.drdroid.chandra.data.impl

import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.data.network.CountryCall
import io.drdroid.chandra.data.repository.Repository
import retrofit2.Response

class RepositoryImpl(
    val countryCall: CountryCall
) : Repository {

//    val countryCall: CountryCall by inject()

    override suspend fun getCountries(): Response<List<CountryModel>> = countryCall.getCountries()
}