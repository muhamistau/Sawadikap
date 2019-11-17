package com.sawadikap.sawadikap.ui.account.edit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sawadikap.sawadikap.R

class AccountEditFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_edit, container, false)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveButton -> {
                // TODO: save new user credentials
            }
        }
    }
}
