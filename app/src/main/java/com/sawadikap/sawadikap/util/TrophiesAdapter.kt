package com.sawadikap.sawadikap.util

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.Trophy
import kotlinx.android.synthetic.main.item_trophy.view.*

class TrophiesAdapter(
    private val context: Context,
    private val trophies: ArrayList<Trophy>,
    private val type: Int
) : RecyclerView.Adapter<TrophiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trophy, parent, false)
    )

    override fun getItemCount(): Int {
        return trophies.size
    }

    override fun onBindViewHolder(holder: TrophiesAdapter.ViewHolder, position: Int) {
        val currentTrophy = trophies[position]

        if (type == 0) holder.card.setCardBackgroundColor(Color.GRAY)

        holder.requirement.text = currentTrophy.requirement.toString()
        holder.name.text = currentTrophy.trophyName
    }

    fun setList(newTrophies: List<Trophy>) {
        trophies.clear()
        trophies.addAll(newTrophies)
        Log.d("SetList", "Success")
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view.card
        val requirement: TextView = view.requirements
        val name: TextView = view.trophyName
    }

}