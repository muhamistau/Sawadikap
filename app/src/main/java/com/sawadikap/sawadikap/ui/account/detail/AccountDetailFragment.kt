package com.sawadikap.sawadikap.ui.account.detail


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Trophy
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.ui.authentication.AuthActivity
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_account_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountDetailFragment : Fragment(), View.OnClickListener {

    private var prefs: SharedPreferences? = null
    private lateinit var trophies: ArrayList<Trophy>

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

        trophies = ArrayList()

        mainCounter.text =
            arguments?.let { AccountDetailFragmentArgs.fromBundle(it).sedekah.toString() }

        nameText.text = prefs?.getString(Constant.PREF_USERNAME, "")
        emailText.text = prefs?.getString(Constant.PREF_EMAIL, "")

        retrieveTrophy()

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

    private fun retrieveTrophy() {
        val prefs = activity?.getSharedPreferences(Constant.PREF_NAME, Constant.PRIVATE_MODE)
        val userId = prefs?.getInt(Constant.PREF_ID, 0)
        val sawadikapService = SawadikapRemote.create()
        if (userId != null) {
            sawadikapService.getUserTrophy(userId).enqueue(object : Callback<List<Trophy>> {
                override fun onFailure(call: Call<List<Trophy>>, t: Throwable) {
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(
                    call: Call<List<Trophy>>,
                    response: Response<List<Trophy>>
                ) {
                    val data = response.body()
//                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                    if (data != null) {
                        Log.d("COBA", data.toString())
                        trophies.addAll(data)
                        activity?.runOnUiThread {
                            trophyCounter.text = trophies.size.toString()
                        }
                    }
                }
            })
        }
    }
}
