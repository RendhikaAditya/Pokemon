package com.example.pokemon.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ItemRowBinding
import com.example.pokemon.response.PokemonResponse
class MainAdapter(
    var originalDatas: ArrayList<PokemonResponse.Result>,
    var listener: OnAdapterListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), Filterable {

    private var filteredDatas: ArrayList<PokemonResponse.Result> = originalDatas
    private var isAscending = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = filteredDatas[position]

        with(holder.binding) {
            itemText.text = model.name
        }

        holder.itemView.setOnClickListener {
            listener.onClick(model)
        }
    }

    override fun getItemCount() = filteredDatas.size

    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: PokemonResponse.Result)
    }

    fun setData(data: List<PokemonResponse.Result>) {
        originalDatas.addAll(data)
        notifyDataSetChanged()
    }
    fun clear(){
        originalDatas.clear()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    filteredDatas = originalDatas
                } else {
                    val filteredList = ArrayList<PokemonResponse.Result>()
                    for (data in originalDatas) {
                        if (data.name.contains(charString, true)) {
                            filteredList.add(data)
                        }
                    }
                    filteredDatas = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredDatas
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredDatas = results?.values as ArrayList<PokemonResponse.Result>
                notifyDataSetChanged()
            }
        }
    }

    fun sortData(ascending: Boolean) {
        isAscending = ascending
        filteredDatas.sortWith(Comparator { item1, item2 ->
            if (isAscending) {
                item1.name.compareTo(item2.name)
            } else {
                item2.name.compareTo(item1.name)
            }
        })
        notifyDataSetChanged()
    }
}



