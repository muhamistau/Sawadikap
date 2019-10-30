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
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button1.setOnClickListener(this)
        retrieveData()
    }

    private fun retrieveData() {
        val sawadikapService = SawadikapRemote.create()
        sawadikapService.getNumberRecord(26).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("FAILURE", t.message.toString())
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val data = response.body()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                if (data != null) mainCounter.text = data.toString()
            }

        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button1 -> {
                val action = HomeFragmentDirections.actionHomeFragmentToTrophyFragment()
                findNavController().navigate(action)
            }
        }
    }
}
