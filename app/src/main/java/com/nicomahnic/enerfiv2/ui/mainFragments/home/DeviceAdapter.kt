package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ItemDeviceBinding
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse

class DeviceAdapter(
    private val devices : List<DevicesByEmailResponse>,
    private val itemClickListener: OnBookClickListener
) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    interface OnBookClickListener {
        fun onItemClick(position: Int, device: DevicesByEmailResponse)
        fun onDeleteItemClick(position: Int, device: DevicesByEmailResponse)
    }

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

        fun render(device: DevicesByEmailResponse){
            binding.tvDeviceName.text = device.name
        }

        fun getItem (device: DevicesByEmailResponse, position: Int) {
            itemView.setOnClickListener { itemClickListener.onItemClick(position, device) }
            binding.btnDelete.setOnClickListener { itemClickListener.onDeleteItemClick(position, device) }
        }
    }

}