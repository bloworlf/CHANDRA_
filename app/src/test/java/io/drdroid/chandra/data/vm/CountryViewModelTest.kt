package io.drdroid.chandra.data.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.drdroid.chandra.data.impl.RepositoryImpl
import io.drdroid.chandra.utils.TestUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.internal.http.RealResponseBody
import okio.Buffer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class CountryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repo: RepositoryImpl

    private lateinit var viewModel: CountryViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onStart() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = CountryViewModel(repo)
    }

    @After
    fun onFinish() {
        Mockito.clearAllCaches()
    }

    @Test
    fun getCountries() = runTest {
        Mockito.`when`(repo.getCountries()).thenReturn(
            Response.success(TestUtils.countryList)
        )
    }

    @Test
    fun getCountries_Empty_Body_Success() = runTest {
        Mockito.`when`(repo.getCountries()).thenReturn(
            Response.success(emptyList())
        )

        assertEquals(repo.getCountries().isSuccessful, repo.getCountries().isSuccessful)
        assertEquals(0, repo.getCountries().body()?.size)
    }

    @Test
    fun getCountries_Error_Response() = runTest {
        Mockito.`when`(repo.getCountries()).thenReturn(
            Response.error(404, RealResponseBody("", 0, Buffer()))
        )

        assertTrue(repo.getCountries().code() == 404)
        assertTrue(!repo.getCountries().isSuccessful)
    }
}