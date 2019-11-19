package com.sawadikap.sawadikap.ui.main.history


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
import com.sawadikap.sawadikap.data.entity.Request
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.util.RequestsAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var requests: ArrayList<Request>
    private lateinit var requestsAdapter: RequestsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requests = ArrayList()
        requestsAdapter = RequestsAdapter(activity as Context, requests)

        historyDone.adapter = requestsAdapter
        historyDone.layoutManager = LinearLayoutManager(activity)

        retrieveRequests()
    }

    private fun retrieveRequests() {
        val service = SawadikapRemote.create()
        service.getUseRequest(26).enqueue(object : Callback<List<Request>> {
            override fun onFailure(call: Call<List<Request>>, t: Throwable) {
                Log.d("FAILURE", t.message.toString())
            }

            override fun onResponse(call: Call<List<Request>>, response: Response<List<Request>>) {
                val data = response.body()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                if (data != null) {
                    Log.d("COBA", data.toString())
                    requests.addAll(data)
                    requestsAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}
