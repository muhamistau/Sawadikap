package com.sawadikap.sawadikap.ui.main.wardobe


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sawadikap.sawadikap.R
import kotlinx.android.synthetic.main.fragment_history.*

class WardrobeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wardrobe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.wardrobeFragment, R.id.historyFragment)
        )
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
