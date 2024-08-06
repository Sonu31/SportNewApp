package com.example.sportapps.ui.mainScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.databinding.RowItemBinding
import com.example.sportapps.response.Data
import com.example.sportapps.utils.ItemClickListener

class SportListAdapter(
    var list: List<Data>, var context: Context, private val onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<SportListAdapter.ViewHolder>(), Filterable {

    private var itemModelListFilter = ArrayList<Data>()

    init {
        itemModelListFilter.addAll(list)
    }

    inner class ViewHolder(var bind: RowItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(data: Data) {
            bind.nsrsSportId.text = data.nsrs_sport_id.toString()
            bind.spNameid.text = data.sport_name
            bind.rfSportDbName.text = data.rf_sport_db_name
            bind.statusid.text = data.status
            bind.deleteid.setOnClickListener {
                onItemClickListener.onClick(data, adapterPosition)
            }
        }
    }

    fun updateData(itemModelList: List<Data>) {
        this.list = itemModelList
        itemModelListFilter.clear()
        itemModelListFilter.addAll(itemModelList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (charSequence.isNullOrEmpty()) {
                    filterResults.count = itemModelListFilter.size
                    filterResults.values = itemModelListFilter
                } else {
                    val searchStr = charSequence.toString().lowercase()
                    val filteredList = itemModelListFilter.filter {
                        it.sport_name?.lowercase()?.contains(searchStr) == true
                    }
                    filterResults.count = filteredList.size
                    filterResults.values = filteredList
                }
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                list = filterResults?.values as List<Data>
                notifyDataSetChanged()
            }
        }
    }
}
