package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage

// STATE
data class MeasureDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: MeasureState
)

//VIEW EFFECT
sealed class MeasureAction {
    object OK: MeasureAction()
}

// VIEW EVENT
sealed class MeasureEvent {
    object LoadData: MeasureEvent()
    data class AddPoint(val data: Voltage): MeasureEvent()
}

// VIEW STATE
sealed class MeasureState {
    object Initial : MeasureState()
    object Plot : MeasureState()
    object AddPointToPlot : MeasureState()
}