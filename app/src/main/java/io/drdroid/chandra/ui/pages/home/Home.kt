package io.drdroid.chandra.ui.pages.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.chandra.R
import io.drdroid.chandra.data.vm.CountryViewModel
import io.drdroid.chandra.databinding.ActivityHomeBinding
import io.drdroid.chandra.ui.BaseActivity
import io.drdroid.chandra.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class Home : BaseActivity() {

    lateinit var bind: ActivityHomeBinding
    private lateinit var controller: NavController

    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.navigation, null)
        Utils.setLightStatusBar(this)

        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        toolbar = bind.toolbar
        setSupportActionBar(toolbar)

        controller = findNavController(R.id.fragment)
        val navConfig = AppBarConfiguration(
            setOf(R.id.listCountryFragment, R.id.countryDetailsFragment)
        )

        controller.setGraph(R.navigation.navigation_home, intent.extras)
        setupActionBarWithNavController(controller, navConfig)

        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

            }
        }

//        viewModel.getCountries()
//
//        viewModel.countries.observe(this) {
//
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    fun showToolbar() {
        if (this::bind.isInitialized) {
            bind.appBar.setExpanded(true, true)
        }
    }

    fun titleColor(color: Int) {
        toolbar.setTitleTextColor(color)

        toolbar.navigationIcon?.let {
            val wrapDrawable = DrawableCompat.wrap(toolbar.navigationIcon!!)
            DrawableCompat.setTint(wrapDrawable, color)
            toolbar.navigationIcon = wrapDrawable
        }

        for (i in 0 until toolbar.menu.size()) {
            try {
                val wDrawable = DrawableCompat.wrap(toolbar.menu.getItem(i).icon!!)
                DrawableCompat.setTint(wDrawable, color)
                toolbar.menu.getItem(i).icon = wDrawable
            } catch (e: Exception) {
            }
        }
    }
}