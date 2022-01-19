package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentMeasureBinding
import com.nicomahnic.enerfiv2.model.local.Voltage
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.ArrayList


@AndroidEntryPoint
class MeasureFragment : BaseFragment<MeasureDataState, MeasureAction, MeasureEvent, MeasureVM>
    (R.layout.fragment_measure)
{
    val args: MeasureFragmentArgs by navArgs()
    override val viewModel: MeasureVM by viewModels()
    private lateinit var binding: FragmentMeasureBinding
    private lateinit var v: View
    private lateinit var timeStamps: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMeasureBinding.bind(view)
        v = view

        // set listener
        SignalChange.binding = binding
        binding.voltageChart.setOnChartValueSelectedListener(SignalChange)
        binding.btnAddRandomData.setOnClickListener(clickListenerAddData)

        activity?.actionBar?.title = "CubicLineChart"

        // no description text
        binding.voltageChart.description.isEnabled = true
        binding.voltageChart.description.text = "by Enerfi"

        // enable touch gestures
        binding.voltageChart.setTouchEnabled(true)

        // enable scaling and dragging
        binding.voltageChart.isDragEnabled = true
        binding.voltageChart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        binding.voltageChart.setPinchZoom(false)

        binding.voltageChart.setDrawGridBackground(false)

        val x: XAxis = binding.voltageChart.xAxis
        x.isEnabled = false

        val y: YAxis = binding.voltageChart.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.BLACK
        y.axisMinimum = 170F
        y.axisMaximum = 250F

        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        y.setDrawGridLines(false)
        y.axisLineColor = Color.BLACK

        binding.voltageChart.axisRight.isEnabled = false

        binding.voltageChart.legend.isEnabled = true

        // get the legend (only possible after setting data)
        val l: Legend = binding.voltageChart.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE

        binding.voltageChart.animateXY(2000, 50)

        viewModel.process(MeasureEvent.LoadData(args.mail, args.passwd, args.mac))
    }

    object SignalChange : OnChartValueSelectedListener {

        lateinit var binding: FragmentMeasureBinding

        override fun onValueSelected(e: Entry?, h: Highlight?) {
            Log.d("NM","Entry selected $e")
            val x = "%.1f".format(e?.x)
            binding.tvDate.text = x
            val y = "%.1f".format(e?.y)
            binding.tvVoltage.text = y
        }

        override fun onNothingSelected() {
            Log.i("Entry selected", "onNothingSelected")
        }

    }

    override fun renderViewState(viewState: MeasureDataState) {
        when (viewState.state) {
            is MeasureState.AddPointToPlot -> {
                addPointToPlot(viewState.voltage!!.first())
            }
            is MeasureState.Plot -> {
                timeStamps = viewState.timeStamp!!
                setData(viewState.voltage)
            }
            else -> {
                Log.d("NM", "${viewState.state}")
            }
        }
    }

    override fun renderViewEffect(viewEffect: MeasureAction) {
        when (viewEffect) {
            is MeasureAction.OK -> {
                Log.d("NM", "$viewEffect")
            }
        }
    }

    private fun updateData(data: LineData){
        binding.voltageChart.notifyDataSetChanged()
        binding.voltageChart.data = data
        binding.voltageChart.setVisibleXRangeMaximum(30F)
        binding.voltageChart.moveViewToX(data.entryCount.toFloat())
        binding.voltageChart.invalidate()
    }

    private fun addPointToPlot(entry: Entry) {
        Log.d("NM","onClickListener")
        val data: LineData = binding.voltageChart.data

        data.addEntry(entry, 0)
        data.notifyDataChanged()
        updateData(data)
    }

    private val clickListenerAddData = View.OnClickListener {
        val data: LineData = binding.voltageChart.data
        val set = data.getDataSetByIndex(0)

        val x = set!!.entryCount.toFloat()
        val y = (Math.random() * 40).toFloat() + 200F
        val point = Voltage(x,y,"MAAAASH")
    }

    private fun setData(entries: List<Entry>?) {

        val set1: LineDataSet
        if (binding.voltageChart.data != null &&
            binding.voltageChart.data.dataSetCount > 0
        ) {
            set1 = binding.voltageChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = entries
            binding.voltageChart.data.notifyDataChanged()
            binding.voltageChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            val values = ArrayList<Entry>()
            values.add(Entry(0F, 220F))

            set1 = if(entries!!.isEmpty()) LineDataSet(values, "Voltage") else LineDataSet(entries, "Voltage")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)

            set1.color = resources.getColor(R.color.endColor)
            set1.fillColor = Color.WHITE
            set1.fillAlpha = 100

            set1.setDrawHorizontalHighlightIndicator(true)
            set1.setDrawVerticalHighlightIndicator(true)
            set1.highLightColor = resources.getColor(R.color.btn_green)
            set1.highlightLineWidth = 2F

            // draw selection line as dashed
            set1.enableDashedHighlightLine(20F, 10F, 0F);

            // set color of filled area
            set1.fillColor = resources.getColor(R.color.startColor)

            set1.fillFormatter = IFillFormatter { dataSet, dataProvider ->
                binding.voltageChart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            updateData(data)
        }
    }
}