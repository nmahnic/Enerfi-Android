package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.local.Voltage
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.repository.local.GetVoltage
import com.nicomahnic.enerfiv2.repository.local.InsertVoltage
import com.nicomahnic.enerfiv2.repository.server.PostFetchRemoteMeasures
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MeasureVM @ViewModelInject constructor(
    private val getVoltage: GetVoltage,
    private val insertVoltage: InsertVoltage,
    private val postFetchRemoteMeasures: PostFetchRemoteMeasures
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
                getRemoteMeasures(viewEvent.mail,viewEvent.passwd, viewEvent.mac)
            }
        }
    }

    private fun getRemoteMeasures(mail : String, passwd : String, mac: String) {
        viewModelScope.launch {
            postFetchRemoteMeasures.request(PostUserAndDumRequest(mail, passwd,mac))
                .catch { e -> Log.d("NM", "postFetchRemoteMeasures Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "postFetchRemoteMeasures Success: ${res.data}")
                            val entries = mutableListOf<Entry>()
                            res.data.forEachIndexed { index, measure ->
                                entries.add(Entry(index.toFloat(), measure.vrms))
                            }
                            viewState = viewState.copy(
                                state = MeasureState.Plot,
                                voltage = entries,
                                timeStamp = res.data.map { it.timeStamp }
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "postFetchRemoteMeasures Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}