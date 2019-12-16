package com.sawadikap.sawadikap.ui.main.wardrobe


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Cloth
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.domain.model.response.ClothResponse
import kotlinx.android.synthetic.main.fragment_wardrobe_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class WardrobeDetailFragment : Fragment(), View.OnClickListener {

    lateinit var cloth: Cloth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wardrobe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cloth = WardrobeDetailFragmentArgs.fromBundle(arguments!!).cloth

        Glide.with(this).load(cloth.photo).into(clothImage1)
        Glide.with(this).load(cloth.photo).into(clothImage2)

        clothName.text = cloth.type
        clothSize.text = cloth.size
        clothGender.text = cloth.gender
        clothAge.text = cloth.age
        clothType.text = cloth.type
        clothStatus.text = cloth.status

        addToBoxButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addToBoxButton -> Toast.makeText(activity, "added", Toast.LENGTH_SHORT).show()
            R.id.deleteButton -> {
                val sawadikapService = SawadikapRemote.create()

                sawadikapService.deleteFromWardrobe(cloth.id)
                    .enqueue(object : Callback<ClothResponse> {
                        override fun onFailure(call: Call<ClothResponse>, t: Throwable) {
                            activity?.runOnUiThread {
                                Toast.makeText(
                                    activity,
                                    "Gagal menghapus pakaian, cek koneksi",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            Log.d("FAILURE", t.message.toString())
                        }

                        override fun onResponse(
                            call: Call<ClothResponse>,
                            response: Response<ClothResponse>
                        ) {
                            if (response.isSuccessful) {
                                activity?.runOnUiThread {
                                    Toast.makeText(
                                        activity,
                                        "Berhasil menghapus pakaian",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                findNavController().navigateUp()
                            }
                        }
                    })
            }
        }
    }

}
