package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import com.github.mikephil.charting.data.Entry
import java.sql.Timestamp

data class MeasureChart(
    val measure: List<Entry>?,
    val unit: String?,
    val legend: String?,
    val timestamp: List<String>?
)
