package com.nicomahnic.enerfiv2.ui.mainFragments.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.Voltage
import com.nicomahnic.enerfiv2.repository.GetVoltage
import com.nicomahnic.enerfiv2.repository.InsertVoltage
import kotlinx.coroutines.launch

class HomeVM @ViewModelInject constructor(
    private val getVoltage: GetVoltage,
    private val insertVoltage: InsertVoltage
) : ViewModel() {

    val voltageData = MutableLiveData<List<Voltage>>()

    fun insetVoltage(x: Int, y: Float){
        viewModelScope.launch {
            insertVoltage.insertVoltage(Voltage(x, y))
        }
        getVoltage()
    }

    fun getVoltage() {
        viewModelScope.launch {
            voltageData.value = getVoltage.getVoltage()
        }
    }

}