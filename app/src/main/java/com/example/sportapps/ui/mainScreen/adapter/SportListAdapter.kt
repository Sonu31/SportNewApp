package com.example.sportapps.ui.mainScreen.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.databinding.RowItemBinding
import com.example.sportapps.response.Data
import com.example.sportapps.ui.mainScreen.MainActivity
import com.example.sportapps.utils.ItemClickListener

class SportListAdapter(
    var list: List<Data>, var context: Context, private val onitemclicklistener: ItemClickListener
) : RecyclerView.Adapter<SportListAdapter.ViewHolder>(), Filterable {

    var itemModelListFilter = ArrayList<Data>()

    inner class ViewHolder(var bind: RowItemBinding, onitemclicklistener: ItemClickListener) :
        RecyclerView.ViewHolder(bind.root) {
    }

    fun adapterSetData(itemModelList : ArrayList<Data>){
        this.list = itemModelList
        this.itemModelListFilter = itemModelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false), onitemclicklistener)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModels = list[position]

//        Glide.with(context).load(list[position].strThumb)
//            .placeholder(R.drawable.img)
////            .thumbnail(Glide.with(context).load(R.raw.load))
//            .into(holder.bind.imgid);
        holder.bind.nsrsSportId.text = list[position].nsrs_sport_id.toString()
        holder.bind.spNameid.text= list[position].sport_name
        holder.bind.rfSportDbName.text= list[position].rf_sport_db_name
        holder.bind.statusid.text= list[position].status
        holder.bind.deleteid.setOnClickListener {
            onitemclicklistener.onClick(itemModels,position)


        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (charsequence == null || charsequence.length<0){
                    filterResults.count = itemModelListFilter.size
                    filterResults.values = itemModelListFilter
                }else{
                    var serchr = charsequence.toString()
                    val itemModel = ArrayList<Data>()
                    for (item in itemModelListFilter){
                        if (item.sport_name?.lowercase()?.contains(serchr.lowercase()) == true){
                            itemModel.add(item)

                        }
                    }
                    filterResults.count = itemModel.size
                    filterResults.values = itemModel
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                list = filterResults!!.values as ArrayList<Data>
                notifyDataSetChanged()
            }
        }

    }
}