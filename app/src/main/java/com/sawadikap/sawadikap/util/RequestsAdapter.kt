package com.sawadikap.sawadikap.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Request
import kotlinx.android.synthetic.main.item_history.view.*

class RequestsAdapter(
    private val context: Context,
    private val requests: ArrayList<Request>,
    private val listener: (Request) -> Unit
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRequest = requests[position]

        if (currentRequest.status.contains("sedekah")) {
            holder.invoiceIcon.setImageResource(R.drawable.ic_check)
            holder.invoiceIcon.setBackgroundColor(context.resources.getColor(R.color.historyGreen))
        } else if (currentRequest.status.contains("proses")) {
            holder.invoiceIcon.setImageResource(R.drawable.ic_arrow_forward)
            holder.invoiceIcon.setBackgroundColor(context.resources.getColor(R.color.historyYellow))
        } else {
            holder.invoiceIcon.setImageResource(R.drawable.ic_close)
            holder.invoiceIcon.setBackgroundColor(context.resources.getColor(R.color.historyRed))
        }

        holder.invoiceId.text = "#${currentRequest.id}"
        holder.invoiceDate.text = currentRequest.time
        holder.invoiceStatus.text = currentRequest.status

        holder.bind(currentRequest, listener)
    }

    fun setList(newTrophies: List<Request>) {
        requests.clear()
        requests.addAll(newTrophies)
        Log.d("SetList", "Success")
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val invoiceIcon: ImageView = view.historyImage
        val invoiceId: TextView = view.invoiceId
        val invoiceDate: TextView = view.invoiceDate
        val invoiceStatus: TextView = view.invoiceStatus

        fun bind(request: Request, listener: (Request) -> (Unit)) {
            view.setOnClickListener { listener(request) }
        }
    }

}