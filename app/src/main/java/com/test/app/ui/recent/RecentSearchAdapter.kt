package com.test.app.ui.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.app.R
import com.test.app.data.db.entity.City
import com.test.app.databinding.LayoutRecentRowBinding

class RecentSearchAdapter(
    var cityList: MutableList<City>?,
    private val listener:OnItemClickListener
) :
    RecyclerView.Adapter<RecentSearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = DataBindingUtil.inflate<LayoutRecentRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_recent_row, parent, false
        )
        return SearchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindData(cityList?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return cityList.orEmpty().size
    }

    class SearchViewHolder(
        private val binding: LayoutRecentRowBinding,
        private val listener:OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: City?) {
            binding.cityName.text = item?.cityName
            binding.countryName.text = item?.CountryName

            itemView.setOnClickListener {
                listener.onItemClick(item?.id)
            }
        }
    }

    fun setData(cities: List<City>?) {
        if (cityList != null) {
            cityList?.clear()
            cityList?.let { list -> cities?.let(list::addAll) }
        } else {
            cityList = cities?.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        cityList?.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnItemClickListener{
        fun onItemClick(id:Int?)
    }
}