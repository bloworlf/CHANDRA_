package io.drdroid.chandra.ui.pages.home

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.chandra.R

class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var linearlayout: LinearLayout
    var name: TextView
    var region: TextView
    var capital: TextView
    var code: TextView

    init {
        linearlayout = itemView.findViewById(R.id.linearlayout)
        name = itemView.findViewById(R.id.name)
        region = itemView.findViewById(R.id.region)
        capital = itemView.findViewById(R.id.capital)
        code = itemView.findViewById(R.id.code)
    }
}
