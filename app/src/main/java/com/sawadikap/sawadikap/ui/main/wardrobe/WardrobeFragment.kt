package com.sawadikap.sawadikap.ui.main.wardrobe


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Cloth
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.util.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_wardrobe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WardrobeFragment : Fragment() {

    private lateinit var clothes: ArrayList<Cloth>
    private lateinit var clothesAdapter: ClothesAdapter

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

        clothes = ArrayList()

        clothesAdapter = ClothesAdapter(activity as Context, clothes) {
            //            val directions = ListFragmentDirections
//                .actionListFragmentToDetailFragment(it)
//            findNavController().navigate(directions)
        }

        clothesRecycler.adapter = clothesAdapter
        clothesRecycler.layoutManager = GridLayoutManager(activity, 2)
        clothesRecycler.addItemDecoration(MarginItemDecoration())
        retrieveData()
    }

    private fun retrieveData() {
        val sawadikapService = SawadikapRemote.create()
        sawadikapService.getUserClothes(26).enqueue(object : Callback<List<Cloth>> {
            override fun onFailure(call: Call<List<Cloth>>, t: Throwable) {
                Log.d("FAILURE", t.message.toString())
            }

            override fun onResponse(call: Call<List<Cloth>>, response: Response<List<Cloth>>) {
                val data = response.body()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                if (data != null) {
                    Log.d("COBA", data.toString())
                    clothes.addAll(data)
                    clothesAdapter.setList(clothes)
                }
            }

        })
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
