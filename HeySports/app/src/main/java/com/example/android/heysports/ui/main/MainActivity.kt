package com.example.android.heysports.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.heysports.R
import com.example.android.heysports.databinding.ActivityMainBinding
import com.example.android.heysports.util.extension.setUpWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val bottomNavView by lazy {
        binding.navigation
    }
    private var navController: LiveData<NavController>? = null

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    private val viewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = viewModel

        if (savedInstanceState == null) {
            setUpBottomNavigation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.value?.navigateUp() ?: false
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setUpBottomNavigation() {
        bottomNavView.menu.forEach {
            bottomNavView.findViewById<View>(it.itemId).setOnLongClickListener {
                true
            }
        }

        val navGraphIds = listOf(R.navigation.nav_home)

        val controller = bottomNavView.setUpWithNavController(
            navGraphIds, supportFragmentManager, binding.navFragmentContainer.id, intent
        )
        controller.observe(this, Observer { navController->
            setupActionBarWithNavController(navController)
        })

        navController = controller
    }
}