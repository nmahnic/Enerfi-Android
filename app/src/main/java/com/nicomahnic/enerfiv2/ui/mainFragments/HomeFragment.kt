package com.nicomahnic.enerfiv2.ui.mainFragments

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentHomeBinding
import java.util.ArrayList
import kotlin.math.roundToInt


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

//        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        blankPasswd(prefs)

        activity?.actionBar?.title = "CubicLineChart"

        binding.chart.setViewPortOffsets(0F, 0F, 0F, 0F)
        binding.chart.setBackgroundColor(resources.getColor(R.color.startColor))

        // no description text
        binding.chart.description.isEnabled = false

        // enable touch gestures
        binding.chart.setTouchEnabled(true)

        // enable scaling and dragging
        binding.chart.isDragEnabled = true
        binding.chart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        binding.chart.setPinchZoom(false)

        binding.chart.setDrawGridBackground(false)
        binding.chart.maxHighlightDistance = 300f

        val x: XAxis = binding.chart.xAxis
        x.isEnabled = false

        val y: YAxis = binding.chart.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.BLACK
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        y.setDrawGridLines(false)
        y.axisLineColor = Color.BLACK
        y.axisMinimum = 0F
        y.axisMaximum = 300F

        binding.chart.axisRight.isEnabled = false

        // add data
        setData(100, 40F)

        // set listener
        SignalChange.binding = binding
        binding.chart.setOnChartValueSelectedListener(SignalChange)
        binding.btnAddRandomData.setOnClickListener(clickListenerAddData)

        binding.chart.legend.isEnabled = false

        binding.chart.animateXY(2000, 50)

        // don't forget to refresh the drawing
        binding.chart.invalidate()
    }

    private fun setData(count: Int, range: Float) {
        val values = ArrayList<Entry>()
        for (i in 0 until count) {
            val `val` = (Math.random() * (range + 1)).toFloat() + 200
            values.add(Entry(i.toFloat(), `val`))
        }
        val set1: LineDataSet
        if (binding.chart.data != null &&
            binding.chart.data.dataSetCount > 0
        ) {
            set1 = binding.chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            binding.chart.data.notifyDataChanged()
            binding.chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.WHITE)
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.color = Color.WHITE
            set1.fillColor = Color.WHITE
            set1.fillAlpha = 100

            set1.setDrawHorizontalHighlightIndicator(true)
            set1.setDrawVerticalHighlightIndicator(true)
            set1.highLightColor = resources.getColor(R.color.btn_green)
            set1.highlightLineWidth = 2F
            set1.enableDashedHighlightLine(20F, 10F, 0F);

            set1.fillFormatter = IFillFormatter { dataSet, dataProvider ->
                binding.chart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            binding.chart.data = data

            // limit the number of visible entries
            binding.chart.setVisibleXRangeMaximum(30F)

            // move to the latest entry
            binding.chart.moveViewToX(data.entryCount.toFloat())
        }
    }

    private fun blankPasswd(prefs: SharedPreferences) {
        with(prefs.edit()) {
            putString("username", "")
            putString("password", "")
            apply()
        }
    }


    object SignalChange : OnChartValueSelectedListener {

        lateinit var binding: FragmentHomeBinding

        override fun onValueSelected(e: Entry?, h: Highlight?) {
            Log.i("Entry selected", e.toString())
            Log.i(
                "LOW HIGH",
                "low: " + binding.chart.lowestVisibleX + ", high: " + binding.chart.highestVisibleX
            )
            Log.i(
                "MIN MAX",
                "xMin: " + binding.chart.xChartMin + ", xMax: " + binding.chart.xChartMax + ", yMin: " + binding.chart.yChartMin + ", yMax: " + binding.chart.yChartMax
            )
            binding.tvData1.text = e?.x?.roundToInt().toString()
            val y = "%.1f".format(e?.y)
            binding.tvData2.text = y
        }

        override fun onNothingSelected() {
            Log.i("Entry selected", "onNothingSelected")
        }

    }

    private val clickListenerAddData = View.OnClickListener {
        Log.d("NM","onClickListener")
        val data: LineData = binding.chart.data

        val set = data.getDataSetByIndex(0)

        data.addEntry(
            Entry(set!!.entryCount.toFloat(), (Math.random() * 40).toFloat() + 200F),
            0
        )
        data.notifyDataChanged()

        // let the chart know it's data has changed
        binding.chart.notifyDataSetChanged()

        // set data
        binding.chart.data = data

        // limit the number of visible entries
        binding.chart.setVisibleXRangeMaximum(30F)

        // move to the latest entry
        binding.chart.moveViewToX(data.entryCount.toFloat())

        // don't forget to refresh the drawing
        binding.chart.invalidate()

    }
}