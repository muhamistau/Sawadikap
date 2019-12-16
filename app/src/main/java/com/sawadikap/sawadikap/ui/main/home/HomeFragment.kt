package com.sawadikap.sawadikap.ui.main.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), View.OnClickListener {

    var sedekah: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trophy.setOnClickListener(this)
        account.setOnClickListener(this)
        retrieveData()
    }

    private fun retrieveData() {
        val prefs = activity?.getSharedPreferences(Constant.PREF_NAME, Constant.PRIVATE_MODE)
        val userId = prefs?.getInt(Constant.PREF_ID, 0)
        val sawadikapService = SawadikapRemote.create()
        if (userId != null) {
            sawadikapService.getNumberRecord(userId).enqueue(object : Callback<Int> {
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    val data = response.body()
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                    try {
                        if (data != null) {
                            sedekah = data
                            mainCounter.text = data.toString()
                        }
                    } catch (e: Exception) {
                        Log.d("SEDEKAH_COUNTER", data.toString())
                    }
                }

            })
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.trophy -> {
                val action = HomeFragmentDirections.actionHomeFragmentToTrophyFragment()
                findNavController().navigate(action)
            }

            R.id.account -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToAccountDetailFragment(sedekah)
                findNavController().navigate(action)
            }
        }
    }
}
