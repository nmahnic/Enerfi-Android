package com.nicomahnic.enerfiv2.ui.mainFragments.measure

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

class MeasureVM @ViewModelInject constructor(
    private val getVoltage: GetVoltage,
    private val insertVoltage: InsertVoltage
) : BaseViewModel<MeasureDataState, MeasureAction, MeasureEvent>(){

    init {
        viewState = MeasureDataState(
            state = MeasureState.Initial
        )
    }

    override fun process(viewEvent: MeasureEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is MeasureEvent.LoadData -> {
                getVoltage()
            }
            is MeasureEvent.AddPoint -> {
                insetVoltage(viewEvent.data.x, viewEvent.data.y)
            }
        }
    }

    private fun insetVoltage(x: Float, y: Float){
        viewModelScope.launch {
            insertVoltage.task(Voltage(x, y, "MAAAASH"))
                .catch { e -> Log.d("NM", "insertVoltage Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "insertVoltage Success: ${res.data}")
                            viewState = viewState.copy(
                                state = MeasureState.AddPointToPlot,
                                data = listOf<Entry>(Entry(x,y))
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "insertVoltage Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getVoltage() {
        viewModelScope.launch {
            getVoltage.task()
                .catch { e -> Log.d("NM", "getVoltage Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "getVoltage Success: ${res.data}")
                            val entries = mutableListOf<Entry>()
                            res.data.forEach {
                                entries.add(Entry(it.x, it.y))
                            }
                            viewState = viewState.copy(
                                state = MeasureState.Plot,
                                data = entries
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "getVoltage Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}