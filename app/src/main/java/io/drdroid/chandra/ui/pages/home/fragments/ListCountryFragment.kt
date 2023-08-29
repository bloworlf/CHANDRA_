package io.drdroid.chandra.ui.pages.home.fragments

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.drdroid.chandra.R
import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.data.vm.CountryViewModel
import io.drdroid.chandra.databinding.FragmentListCountryBinding
import io.drdroid.chandra.ui.BaseFragment
import io.drdroid.chandra.ui.pages.home.CountryAdapter
import io.drdroid.chandra.ui.pages.home.EmptyDataObserver
import io.drdroid.chandra.ui.pages.home.Home
import io.drdroid.chandra.utils.Utils
import io.drdroid.chandra.utils.Utils.colorTransition
import io.drdroid.chandra.utils.Utils.hideKeyboard
import jp.wasabeef.recyclerview.animators.LandingAnimator
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCountryFragment : BaseFragment() {

    private lateinit var bind: FragmentListCountryBinding

    //View model injection using Koin way
    private val viewModel by viewModel<CountryViewModel>()

    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var manager: GridLayoutManager

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCountries {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        setHasOptionsMenu(true)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.removeItemDecorationAt(0)
//            recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 10, true))
            manager.spanCount = 2
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
        } else {
            recyclerView.removeItemDecorationAt(0)
//            recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10, true))
            manager.spanCount = 1
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentListCountryBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        swipeRefresh = bind.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            viewModel.getCountries {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                if (swipeRefresh.isRefreshing) {
                    swipeRefresh.isRefreshing = false
                }
            }
        }

        recyclerView = bind.recyclerView
        recyclerView.itemAnimator = LandingAnimator()
        manager = GridLayoutManager(
            requireContext(),
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 2 else 1
        )
        recyclerView.layoutManager = manager

        viewModel.countries.observe(viewLifecycleOwner) {
            swipeRefresh.isRefreshing = false
            setupAdapter(it)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        searchView = (menu.findItem(R.id.search).actionView as SearchView)
        searchView!!.maxWidth = Integer.MAX_VALUE

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.length <= 2) {
//                    Toast.makeText(
//                        this@ListCountryFragment.requireContext(),
//                        "Query string too short",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return false
//                }
//                makeQuery(query)
//                searchView!!.hideKeyboard()
//                return true
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }
        })
    }

    private fun setupAdapter(list: List<CountryModel>) {
        adapter = CountryAdapter(requireContext(), list, findNavController()) { upper, lower ->
//            (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(
//                ColorDrawable(upper)
//            )
//            requireActivity().window.statusBarColor = upper
//
//            bind.root.colorTransition(lower)
//
//            if (Utils.isDark(upper)) {
//                Utils.clearLightStatusBar(requireActivity())
//                (activity as Home).titleColor(Color.WHITE)
//
//            } else {
//                Utils.setLightStatusBar(requireActivity())
//                (activity as Home).titleColor(Color.BLACK)
//
//            }
        }
        recyclerView.adapter = adapter

        val emptyDataObserver = EmptyDataObserver(recyclerView, bind.emptyDataParent.root)
        adapter.registerAdapterDataObserver(emptyDataObserver)
    }
}