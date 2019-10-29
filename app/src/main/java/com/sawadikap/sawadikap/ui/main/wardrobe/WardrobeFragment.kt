package com.sawadikap.sawadikap.ui.main.wardrobe


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.util.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_wardrobe.*

class WardrobeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_wardrobe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        clothesRecycler.addItemDecoration(MarginItemDecoration())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.wardrobe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.box -> {
                val action = WardrobeFragmentDirections.actionWardrobeFragmentToBoxFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}