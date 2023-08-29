package io.drdroid.chandra.data.impl

import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.data.network.CountryCall
import io.drdroid.chandra.data.repository.Repository
import io.drdroid.chandra.utils.TestUtils
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.internal.http.RealResponseBody
import okio.Buffer
import okio.BufferedSource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class RepositoryImplTest {

    lateinit var repo: Repository

    @Mock
    lateinit var countryCall: CountryCall

    @Before
    fun onStart() {
        MockitoAnnotations.openMocks(this)

        repo = RepositoryImpl(countryCall)
    }

    @After
    fun onFinish() {
        Mockito.clearAllCaches()
    }

    @Test
    fun getCountries() = runTest {
        Mockito.`when`(countryCall.getCountries()).thenReturn(
            Response.success(
                TestUtils.countryList
            )
        )
        assertEquals(countryCall.getCountries().isSuccessful, repo.getCountries().isSuccessful)
        assertEquals(countryCall.getCountries().body(), repo.getCountries().body())
    }

    @Test
    fun getCountries_Empty_Body_Success() = runTest {
        Mockito.`when`(countryCall.getCountries()).thenReturn(
            Response.success(emptyList())
        )

        assertEquals(countryCall.getCountries().isSuccessful, repo.getCountries().isSuccessful)
        assertEquals(0, repo.getCountries().body()?.size)
    }

    @Test
    fun getCountries_Error_Response() = runTest {
        Mockito.`when`(countryCall.getCountries()).thenReturn(
            Response.error(404, RealResponseBody("", 0, Buffer()))
        )

        assertTrue(repo.getCountries().code() == 404)
        assertTrue(!repo.getCountries().isSuccessful)
    }
}