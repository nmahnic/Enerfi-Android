package com.nicomahnic.enerfiv2.ui.mainFragments.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage
import com.nicomahnic.enerfiv2.repository.GetVoltage
import com.nicomahnic.enerfiv2.repository.InsertVoltage
import com.nicomahnic.enerfiv2.ui.mainFragments.HomeDataState
import com.nicomahnic.enerfiv2.ui.mainFragments.HomeAction
import com.nicomahnic.enerfiv2.ui.mainFragments.HomeEvent
import com.nicomahnic.enerfiv2.ui.mainFragments.HomeState
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeVM @ViewModelInject constructor(
    private val getVoltage: GetVoltage,
    private val insertVoltage: InsertVoltage
) : BaseViewModel<HomeDataState, HomeAction, HomeEvent>(){

    init {
        viewState = HomeDataState(
            state = HomeState.Initial
        )
    }

    override fun process(viewEvent: HomeEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is HomeEvent.GenerateData -> {
//                setData(3, 40F)
            }
            is HomeEvent.LoadData -> {
                getVoltage()
            }
            is HomeEvent.AddPoint -> {
                insetVoltage(viewEvent.data.x, viewEvent.data.y)
            }
            else -> {

            }
        }
    }

    private fun insetVoltage(x: Float, y: Float){
        viewModelScope.launch {
            insertVoltage.insertVoltage(Voltage(x, y))
                .catch { e -> Log.d("NM", "insertVoltage Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "insertVoltage Success: ${res.data}")
                            viewState = viewState.copy(
                                state = HomeState.AddPointToPlot,
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
            getVoltage.getVoltage()
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
                                state = HomeState.Plot,
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

    private fun setData(count: Int, range: Float) {
        for (i in 0 until count) {
            val `val` = (Math.random() * (range + 1)).toFloat() + 200
            insetVoltage(i.toFloat(), `val`)
        }
    }

}