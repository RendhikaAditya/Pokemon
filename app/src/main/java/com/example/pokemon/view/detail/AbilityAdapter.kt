package com.example.pokemon.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ItemRowBinding
import com.example.pokemon.response.DetailResponse
import com.example.pokemon.response.PokemonResponse
class AbilityAdapter(
    var originalDatas: ArrayList<DetailResponse.Ability>,
    var listener: OnAdapterListener
) : RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = originalDatas[position]

        with(holder.binding) {
            itemText.text = model.ability.name
        }

        holder.itemView.setOnClickListener {
            listener.onClick(model)
        }
    }

    override fun getItemCount() =originalDatas.size

    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: DetailResponse.Ability)
    }

    fun setData(data: List<DetailResponse.Ability>) {
        originalDatas.addAll(data)
        notifyDataSetChanged()
    }

}



