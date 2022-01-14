package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

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

class DeviceRegisterVM @ViewModelInject constructor(

) : BaseViewModel<DeviceRegisterDataState, DeviceRegisterAction, DeviceRegisterEvent>(){

    init {
        viewState = DeviceRegisterDataState(
            state = DeviceRegisterState.Initial
        )
    }

    override fun process(viewEvent: DeviceRegisterEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is DeviceRegisterEvent.LoadData -> {

            }
        }
    }

}