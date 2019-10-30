package com.sawadikap.sawadikap.ui.trophy


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Trophy
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.util.TrophiesAdapter
import kotlinx.android.synthetic.main.fragment_trophy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrophyFragment : Fragment() {

    private lateinit var trophies: ArrayList<Trophy>
    private lateinit var trophiesAdapter: TrophiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trophy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trophies = ArrayList()
        trophiesAdapter = TrophiesAdapter(activity as Context, trophies)

        trophyDone.adapter = trophiesAdapter
        trophyDone.layoutManager = LinearLayoutManager(activity)

        retrieveTrophy()
    }

    private fun retrieveTrophy() {
        val sawadikapService = SawadikapRemote.create()
        sawadikapService.getUserTrophy(26).enqueue(object : Callback<List<Trophy>> {
            override fun onFailure(call: Call<List<Trophy>>, t: Throwable) {
                Log.d("FAILURE", t.message.toString())
            }

            override fun onResponse(call: Call<List<Trophy>>, response: Response<List<Trophy>>) {
                val data = response.body()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                if (data != null) {
                    Log.d("COBA", data.toString())
                    trophies.addAll(data)
                    trophiesAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}
