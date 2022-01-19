package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.repository.server.PostFetchRemoteMeasures
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MeasureVM @ViewModelInject constructor(
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
                .collect { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "postFetchRemoteMeasures Success: ${res.data}")
                            val timestamp = res.data.map { it.timeStamp }
                            viewState = viewState.copy(
                                state = MeasureState.Plot,
                                voltage = res.data.mapIndexed { index, measure -> Entry(index.toFloat(), measure.vrms) },
                                current = res.data.mapIndexed { index, measure -> Entry(index.toFloat(), measure.irms) },
                                activePower = res.data.mapIndexed { index, measure -> Entry(index.toFloat(), measure.activePower) },
                                timeStamp = timestamp,
                                cosPhi = res.data.last().cosPhi,
                                thd = res.data.last().thd,
                                powerFactor = res.data.last().powerFactor
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "postFetchRemoteMeasures Failure: ${res.exception}")
                        }
                    }
                }
        }
    }

}