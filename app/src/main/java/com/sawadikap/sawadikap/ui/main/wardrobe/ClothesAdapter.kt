package com.sawadikap.sawadikap.ui.main.wardrobe

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Cloth
import kotlinx.android.synthetic.main.item_clothes.view.*

class ClothesAdapter(
    private val context: Context,
    private val clothes: ArrayList<Cloth>,
    private val listener: (Cloth) -> Unit
) : RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clothes, parent, false)
    )

    override fun getItemCount(): Int {
        return clothes.size
    }

    override fun onBindViewHolder(holder: ClothesAdapter.ViewHolder, position: Int) {
        val currentCloth = clothes[position]

        Glide.with(context).clear(holder.image)
        Glide.with(context).load(currentCloth.photo).into(holder.image)
        holder.name.text = currentCloth.type
        holder.status.text = currentCloth.status

        holder.bind(currentCloth, listener)
    }

    fun setList(newClothes: List<Cloth>) {
        clothes.addAll(newClothes)
        Log.d("SetList", "Success")
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.clothImage
        val name: TextView = view.clothName
        val status: TextView = view.clothStatus

        fun bind(cloth: Cloth, listener: (Cloth) -> Unit) {
            view.setOnClickListener { listener(cloth) }
        }
    }

}