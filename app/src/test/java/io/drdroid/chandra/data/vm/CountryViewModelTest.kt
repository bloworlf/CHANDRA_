package io.drdroid.chandra.data.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.drdroid.chandra.data.impl.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

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
    fun getCountries() {
    }
}