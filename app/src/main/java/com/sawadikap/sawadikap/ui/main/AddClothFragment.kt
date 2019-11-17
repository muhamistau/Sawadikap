package com.sawadikap.sawadikap.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sawadikap.sawadikap.R

class AddClothFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_cloth, container, false)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addToWardrobeButton -> {
                // TODO: add current cloth to Wardrobe
            }
        }
    }
}
