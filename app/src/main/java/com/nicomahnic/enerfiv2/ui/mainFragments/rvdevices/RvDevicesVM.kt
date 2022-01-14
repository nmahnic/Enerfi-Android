package com.nicomahnic.enerfiv2.ui.mainFragments.rvdevices

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage
import com.nicomahnic.enerfiv2.repository.GetVoltage
import com.nicomahnic.enerfiv2.repository.InsertVoltage
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RvDevicesVM @ViewModelInject constructor(

) : BaseViewModel<RvDevicesDataState, RvDevicesAction, RvDevicesEvent>(){

    init {
        viewState = RvDevicesDataState(
            state = RvDevicesState.Initial
        )
    }

    override fun process(viewEvent: RvDevicesEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is RvDevicesEvent.LoadData -> {

            }
        }
    }

}