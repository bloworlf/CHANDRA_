package io.drdroid.chandra.ui.pages.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.drdroid.chandra.R
import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.utils.PaletteUtils
import io.drdroid.chandra.utils.Utils
import io.drdroid.chandra.utils.Utils.colorTransition

class CountryAdapter(
    val context: Context,
    val list: List<CountryModel>,
    findNavController: NavController,
    val onClick: (Int, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var filteredList = list.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.model_country, parent, false)
        return CountryHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(h0: RecyclerView.ViewHolder, position: Int) {
        val country = filteredList[position]
        val holder = h0 as CountryHolder

        val countryFlag = "https://flagcdn.com/w320/${country.code?.lowercase()}.png"

        holder.capital.text = country.capital
        holder.code.text = country.code
        holder.region.text = country.region
        holder.name.text = country.name

        var domColor:Int = 0
        var color:Int = 0

        Glide.with(context).asBitmap().load(Uri.parse(countryFlag)).centerCrop()
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {

                    domColor = PaletteUtils.getUpperSideDominantColor(
                        resource
                    )
                    color = PaletteUtils.getLowerSideDominantColor(
                        resource
                    )
                    holder.linearlayout.colorTransition(domColor)

                    if (Utils.isDark(domColor)) {
                        holder.name.setTextColor(Color.WHITE)
                        holder.region.setTextColor(Color.WHITE)
                        holder.capital.setTextColor(Color.WHITE)
                        holder.code.setTextColor(Color.WHITE)
                    } else {
                        holder.name.setTextColor(Color.BLACK)
                        holder.region.setTextColor(Color.BLACK)
                        holder.capital.setTextColor(Color.BLACK)
                        holder.code.setTextColor(Color.BLACK)
                    }

                }
            })

        holder.itemView.setOnClickListener {
            onClick(domColor, color)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filterResults.count = list.size
                    filterResults.values = list
                } else {
                    val str: String = constraint.toString()
                    val resultList = list.filter {
                        it.code?.lowercase()?.contains(str.lowercase()) == true
                                ||
                                it.capital?.lowercase()?.contains(str.lowercase()) == true
                                ||
                                it.name?.lowercase()?.contains(str.lowercase()) == true
                                ||
                                it.region?.lowercase()?.contains(str.lowercase()) == true
                    }
                    filterResults.count = resultList.size
                    filterResults.values = resultList
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<CountryModel>
                notifyDataSetChanged()
            }
        }
    }
}