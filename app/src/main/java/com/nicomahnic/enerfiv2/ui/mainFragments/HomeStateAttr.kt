package com.nicomahnic.enerfiv2.ui.mainFragments

import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage


// STATE
data class HomeDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: HomeState
)

//VIEW EFFECT
sealed class HomeAction {
    object OK: HomeAction()
}

// VIEW EVENT
sealed class HomeEvent {
    object LoadData: HomeEvent()
    object GenerateData: HomeEvent()
    data class AddPoint(val data: Voltage): HomeEvent()
}

// VIEW STATE
sealed class HomeState {
    object Initial : HomeState()
    object Plot : HomeState()
    object AddPointToPlot : HomeState()
}