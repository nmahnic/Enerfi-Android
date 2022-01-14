package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
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
import com.nicomahnic.enerfiv2.databinding.FragmentHomeBinding
import com.nicomahnic.enerfiv2.model.Voltage
import com.nicomahnic.enerfiv2.ui.splashFragments.login.LoginFragmentDirections
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeDataState, HomeAction, HomeEvent, HomeVM>
    (R.layout.fragment_home)
{
    override val viewModel: HomeVM by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        v = view

        // set listener
        SignalChange.binding = binding
        binding.chart.setOnChartValueSelectedListener(SignalChange)
        binding.btnAddRandomData.setOnClickListener(clickListenerAddData)
        binding.btnLogout.setOnClickListener(clickListenerLogout)
        binding.btnConnect.setOnClickListener(clickListenerConnect)

        activity?.actionBar?.title = "CubicLineChart"

        // no description text
        binding.chart.description.isEnabled = true
        binding.chart.description.text = "by Enerfi"

        // enable touch gestures
        binding.chart.setTouchEnabled(true)

        // enable scaling and dragging
        binding.chart.isDragEnabled = true
        binding.chart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        binding.chart.setPinchZoom(false)

        binding.chart.setDrawGridBackground(false)

        val x: XAxis = binding.chart.xAxis
        x.isEnabled = false

        val y: YAxis = binding.chart.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.BLACK
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        y.setDrawGridLines(false)
        y.axisLineColor = Color.BLACK

        binding.chart.axisRight.isEnabled = false

        binding.chart.legend.isEnabled = true

        // get the legend (only possible after setting data)
        val l: Legend = binding.chart.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE

        binding.chart.animateXY(2000, 50)

        viewModel.process(HomeEvent.LoadData)
    }

    object SignalChange : OnChartValueSelectedListener {

        lateinit var binding: FragmentHomeBinding

        override fun onValueSelected(e: Entry?, h: Highlight?) {
            Log.d("NM","Entry selected $e")
            val x = "%.1f".format(e?.x)
            binding.tvData1.text = x
            val y = "%.1f".format(e?.y)
            binding.tvData2.text = y
        }

        override fun onNothingSelected() {
            Log.i("Entry selected", "onNothingSelected")
        }

    }

    override fun renderViewState(viewState: HomeDataState) {
        when (viewState.state) {
            is HomeState.AddPointToPlot -> {
                addPointToPlot(viewState.data!!.first())
            }
            is HomeState.Plot -> {
                setData(viewState.data)
            }
            else -> {
                Log.d("NM", "${viewState.state}")
            }
        }
    }

    override fun renderViewEffect(viewEffect: HomeAction) {
        when (viewEffect) {
            is HomeAction.OK -> {
                Log.d("NM", "$viewEffect")
            }
        }
    }

    private fun updateData(data: LineData){
        binding.chart.notifyDataSetChanged()
        binding.chart.data = data
        binding.chart.setVisibleXRangeMaximum(30F)
        binding.chart.moveViewToX(data.entryCount.toFloat())
        binding.chart.invalidate()
    }

    private fun addPointToPlot(entry: Entry) {
        Log.d("NM","onClickListener")
        val data: LineData = binding.chart.data

        data.addEntry(entry, 0)
        data.notifyDataChanged()
        updateData(data)
    }

    private val clickListenerAddData = View.OnClickListener {
        val data: LineData = binding.chart.data
        val set = data.getDataSetByIndex(0)

        val x = set!!.entryCount.toFloat()
        val y = (Math.random() * 40).toFloat() + 200F
        val point = Voltage(x,y,"MAAAASH")

        viewModel.process(HomeEvent.AddPoint(point))
    }

    private val clickListenerLogout = View.OnClickListener {
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        with(prefs.edit()) {
            putString("userMail", "")
            putString("password", "")
            apply()
        }
        activity?.finish()
    }

    private val clickListenerConnect = View.OnClickListener {
        val action = HomeFragmentDirections.actionHomeFragmentToDeviceRegisterFragment()
        v.findNavController().navigate(action)
    }

    private fun setData(entries: List<Entry>?) {

        val set1: LineDataSet
        if (binding.chart.data != null &&
            binding.chart.data.dataSetCount > 0
        ) {
            set1 = binding.chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = entries
            binding.chart.data.notifyDataChanged()
            binding.chart.notifyDataSetChanged()
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
                binding.chart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            updateData(data)
        }
    }
}