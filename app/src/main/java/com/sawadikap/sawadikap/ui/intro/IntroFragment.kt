package com.sawadikap.sawadikap.ui.intro


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

const val LAYOUT_ID = "layoutid"
const val LAYOUT_POS = "layoutpos"

class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            arguments!!.getInt(LAYOUT_ID, -1), container,
            false
        )
    }

    companion object {
        fun newInstance(layoutId: Int, position: Int): IntroFragment {
            val pane = IntroFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt(LAYOUT_POS, position)
            pane.arguments = args
            return pane
        }
    }

}
