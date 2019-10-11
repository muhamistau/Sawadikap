package com.sawadikap.sawadikap.ui.main.wardobe


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sawadikap.sawadikap.R
import kotlinx.android.synthetic.main.fragment_history.toolbar
import kotlinx.android.synthetic.main.fragment_wardrobe.*

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

        val clothes = ArrayList<Int>()

        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)
        clothes.add(1)

        val clothesListAdapter = ClothesAdapter(activity as Context, clothes) {
            //            val directions = ListFragmentDirections
//                .actionListFragmentToDetailFragment(it)
//            findNavController().navigate(directions)
        }

        clothesRecycler.adapter = clothesListAdapter
        clothesRecycler.layoutManager = GridLayoutManager(activity, 2)
    }
}
