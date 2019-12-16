package com.sawadikap.sawadikap.ui.account.detail


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.ui.authentication.AuthActivity
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_account_detail.*


class AccountDetailFragment : Fragment(), View.OnClickListener {

    private var prefs: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_account_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs =
            activity?.getSharedPreferences(Constant.PREF_NAME, Constant.PRIVATE_MODE)

        nameText.text = prefs?.getString(Constant.PREF_USERNAME, "")
        emailText.text = prefs?.getString(Constant.PREF_EMAIL, "")

        logoutButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.logoutButton -> {
                prefs?.edit()?.clear()?.apply()
                activity?.finishAffinity()
                startActivity(Intent(activity, AuthActivity::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_account, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                val action =
                    AccountDetailFragmentDirections.actionAccountDetailFragmentToAccountEditFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
