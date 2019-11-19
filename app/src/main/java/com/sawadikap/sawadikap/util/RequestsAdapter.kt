package com.sawadikap.sawadikap.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Request
import kotlinx.android.synthetic.main.item_history.view.*

class RequestsAdapter(
    private val context: Context,
    private val requests: ArrayList<Request>
) : RecyclerView.Adapter<RequestsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
    )

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onBindViewHolder(holder: RequestsAdapter.ViewHolder, position: Int) {
        val currentRequest = requests[position]

        holder.invoiceId.text = "#${currentRequest.id}"
        holder.invoiceDate.text = currentRequest.time
        holder.invoiceStatus.text = currentRequest.status
    }

    fun setList(newTrophies: List<Request>) {
        requests.clear()
        requests.addAll(newTrophies)
        Log.d("SetList", "Success")
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val invoiceId: TextView = view.invoiceId
        val invoiceDate: TextView = view.invoiceDate
        val invoiceStatus: TextView = view.invoiceStatus
    }

}