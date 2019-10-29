package com.sawadikap.sawadikap.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sawadikap.sawadikap.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        mainToolbar.setBackgroundColor(Color.TRANSPARENT)
        mainToolbar.setTitleTextColor(Color.BLACK)

        // Setup Bottom Navigation with navController
        navController = Navigation.findNavController(this, R.id.homeNavHostFragment)
        bottomNav.setupWithNavController(navController)

        // Setup AppBar with navigationController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.wardrobeFragment, R.id.historyFragment
            )
        )
        mainToolbar.setupWithNavController(navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment ||
                destination.id == R.id.wardrobeFragment ||
                destination.id == R.id.historyFragment
            ) showBottomNavigation()
            else hideBottomNavigation()
        }
    }

    private fun hideBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottomNav) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                addFab.hide()
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 0
            }
        }
    }

    private fun showBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottomNav) {
            addFab.show()
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 0
        }
    }
}
