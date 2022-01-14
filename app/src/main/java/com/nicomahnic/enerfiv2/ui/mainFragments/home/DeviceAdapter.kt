package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ItemDeviceBinding
import com.nicomahnic.enerfiv2.ui.mainFragments.home.model.Device

class DeviceAdapter(
    private val devices : List<Device>,
    private val onItemClick: (Int, Device) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DeviceViewHolder(layoutInflater.inflate(R.layout.item_device, parent, false))
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.render(devices[position])

        holder.getItem(devices[position], position)
    }

    override fun getItemCount() = devices.size

    inner class DeviceViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemDeviceBinding.bind(view)

        fun render(device: Device){
            binding.tvDeviceName.text = device.name
        }

        fun getItem (device: Device, position: Int): Unit {
            return itemView.setOnClickListener { onItemClick(position, device) }
        }
    }

}