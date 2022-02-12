package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ItemValueBinding


class ItemValueAdapter (
    private val itemValues : List<ItemValue>
) : RecyclerView.Adapter<ItemValueAdapter.ItemValueViewHolder>() {

    inner class ItemValueViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemValueBinding.bind(view)

        fun getItem(position: Int) {
            Log.d("NM", "ItemValueAdapter getItem($position)")
        }

        fun render(position: Int, itemValue: ItemValue){
            binding.tvItemLabel.hint = itemValue.label
            binding.tvItemValue.setText(itemValue.value)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemValueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemValueViewHolder(layoutInflater.inflate(R.layout.item_value, parent, false))
    }

    override fun onBindViewHolder(holder: ItemValueViewHolder, position: Int) {
        holder.getItem(position)
        holder.render(position, itemValues[position])
    }

    override fun getItemCount(): Int = itemValues.size
}