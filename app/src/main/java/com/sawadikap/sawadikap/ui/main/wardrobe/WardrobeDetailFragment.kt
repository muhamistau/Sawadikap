package com.sawadikap.sawadikap.ui.main.wardrobe


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sawadikap.sawadikap.R
import kotlinx.android.synthetic.main.fragment_wardrobe_detail.*

/**
 * A simple [Fragment] subclass.
 */
class WardrobeDetailFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wardrobe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cloth = WardrobeDetailFragmentArgs.fromBundle(arguments!!).cloth

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
            else -> Toast.makeText(activity, "Other", Toast.LENGTH_SHORT).show()
        }
    }

}
