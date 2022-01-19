package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import com.github.mikephil.charting.data.Entry
import java.sql.Timestamp

// STATE
data class MeasureDataState(
    val exception: Exception? = null,
    val voltage: List<Entry>? = null,
    val current: List<Entry>? = null,
    val thd: Float? = null,
    val powerFactor: Float? = null,
    val cosPhi: Float? = null,
    val timeStamp: List<String>? = null,
    val state: MeasureState
)

//VIEW EFFECT
sealed class MeasureAction {
    object OK: MeasureAction()
}

// VIEW EVENT
sealed class MeasureEvent {
    data class LoadData(val mail: String, val passwd: String, val mac: String): MeasureEvent()
}

// VIEW STATE
sealed class MeasureState {
    object Initial : MeasureState()
    object Plot : MeasureState()
    object AddPointToPlot : MeasureState()
}