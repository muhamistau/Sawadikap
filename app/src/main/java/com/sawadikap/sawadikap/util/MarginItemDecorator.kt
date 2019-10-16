package com.sawadikap.sawadikap.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
                top = 12
            }

            left = if (parent.getChildAdapterPosition(view) % 2 == 0) 12
            else 4

            right = if (parent.getChildAdapterPosition(view) % 2 == 0) 4
            else 12

            bottom = 12
        }
    }
}