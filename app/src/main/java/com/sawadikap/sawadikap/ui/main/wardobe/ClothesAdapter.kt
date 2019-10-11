package com.sawadikap.sawadikap.ui.main.wardobe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawadikap.sawadikap.R
import kotlinx.android.synthetic.main.item_clothes.view.*

class ClothesAdapter(
    private val context: Context,
    private val clothes: List<Int>,
    private val listener: (Int) -> Unit
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

    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.clothImage
        val name: TextView = view.clothName
        val status: TextView = view.clothStatus

        fun bind(cloth: Int, listener: (Int) -> Unit) {
            view.setOnClickListener { listener(cloth) }
        }
    }

}