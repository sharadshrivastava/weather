package com.test.app.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

//This class is required to remove divider from last row of recycler view.
class VerticalSpaceItemDecoration(context: Context?, val verticalSpaceHeight: Int) :
    DividerItemDecoration(context, VERTICAL) {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}