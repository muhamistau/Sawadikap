package com.sawadikap.sawadikap.ui.main.wardrobe


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.carteasy.v1.lib.Carteasy
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Cloth
import com.sawadikap.sawadikap.util.BoxAdapter
import kotlinx.android.synthetic.main.fragment_box.*

/**
 * A simple [Fragment] subclass.
 */
class BoxFragment : Fragment() {

    private lateinit var clothes: ArrayList<Cloth>
    private lateinit var boxAdapter: BoxAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clothes = ArrayList()

        val data: Map<Int, Map<*, *>>
        val cs = Carteasy()
        data = cs.ViewAll(activity?.applicationContext)

        var cloth: Cloth
        if (data != null && data.isNotEmpty()) {
            for ((_, innerdata) in data) { //Retrieve the values of the Map by starting from index 0 - zero
                cloth = Cloth()
                //Get the sub values of the Map
                for ((product, value) in innerdata) {
                    println("""${product.toString()}=$value""")

                    when (product) {
                        "id" -> cloth.id = Integer.parseInt(value as String)
                        "photo" -> cloth.photo = value as String?
                        "name" -> cloth.type = value as String?
                        "size" -> cloth.size = value as String?
                        "gender" -> cloth.gender = value as String?
                        "age" -> cloth.age = value as String?
                        "status" -> cloth.status = value as String?
                    }
                }
                clothes.add(cloth)
            }
            Log.d("CART_ITEM", clothes.toString())

            boxAdapter = BoxAdapter(activity as Context, clothes) {
                Toast.makeText(activity, "TEST", Toast.LENGTH_SHORT).show()
            }
            boxRecycler.adapter = boxAdapter
            boxRecycler.layoutManager = LinearLayoutManager(activity)
        }
    }
}
