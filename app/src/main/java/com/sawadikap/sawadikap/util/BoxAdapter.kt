package com.sawadikap.sawadikap.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carteasy.v1.lib.Carteasy
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Cloth
import kotlinx.android.synthetic.main.item_box.view.*

class BoxAdapter(
    private val context: Context,
    private val clothes: ArrayList<Cloth>,
    private val listener: (Cloth) -> Unit
) : RecyclerView.Adapter<BoxAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_box, parent, false)
    )

    override fun getItemCount(): Int {
        return clothes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCloth = clothes[position]

        Glide.with(context).clear(holder.image)
        Glide.with(context).load(currentCloth.photo).into(holder.image)

        holder.name.text = currentCloth.type
        holder.size.text = "Ukuran: ${currentCloth.size}"

        holder.bind(currentCloth, listener)
        holder.delete.setOnClickListener {
            val cs = Carteasy()
            Log.d("CLOTHES_ID", currentCloth.id.toString())
            cs.RemoveId(currentCloth.id.toString(), context.applicationContext)
            clothes.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.clothImage
        val name: TextView = view.name
        val size: TextView = view.size
        val delete: ImageButton = view.deleteFromBox

        fun bind(cloth: Cloth, listener: (Cloth) -> (Unit)) {
            view.setOnClickListener { listener(cloth) }
        }
    }

}