package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ItemMeasureBinding
import java.sql.Timestamp
import java.util.*

class MeasureAdapter(
    private val measures : List<MeasureChart>,
    private val resources: Resources
) : RecyclerView.Adapter<MeasureAdapter.MeasureViewHolder>() {

    private lateinit var resouces: Resources

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MeasureViewHolder(layoutInflater.inflate(R.layout.item_measure, parent, false))
    }

    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {
        holder.render(measures[position])

        holder.getItem(position,measures[position])
    }

    override fun getItemCount() = measures.size

    inner class MeasureViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMeasureBinding.bind(view)

        fun getItem(position: Int, chart: MeasureChart) {
            // set listener

            binding.measureChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    Log.d("NM","Entry selected $e")
                    binding.tvDate.text = chart.timestamp?.get(e?.x!!.toInt())
                    binding.tvMeasure.text = e?.y.toString()
                    binding.tvMeasureUnit.text = chart.unit
                }

                override fun onNothingSelected() {
                    Log.i("Entry selected", "onNothingSelected")
                }

            })
        }

        fun render(chart: MeasureChart){
            resouces = resources

            // no description text
            binding.measureChart.description.isEnabled = true
            binding.measureChart.description.text = "by Enerfi"
            binding.tvMeasureLegend.text = chart.legend

            // enable touch gestures
            binding.measureChart.setTouchEnabled(true)

            // enable scaling and dragging
            binding.measureChart.isDragEnabled = true
            binding.measureChart.setScaleEnabled(true)

            // if disabled, scaling can be done on x- and y-axis separately
            binding.measureChart.setPinchZoom(false)

            binding.measureChart.setDrawGridBackground(false)

            val x: XAxis = binding.measureChart.xAxis
            x.isEnabled = false

            val y: YAxis = binding.measureChart.axisLeft
            y.setLabelCount(6, false)
            y.textColor = Color.BLACK

            y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            y.setDrawGridLines(false)
            y.axisLineColor = Color.BLACK

            binding.measureChart.axisRight.isEnabled = false

            binding.measureChart.legend.isEnabled = true

            // get the legend (only possible after setting data)
            val l: Legend = binding.measureChart.legend

            // draw legend entries as lines
            l.form = Legend.LegendForm.LINE

            binding.measureChart.animateXY(2000, 50)

            setData(chart.measure, chart.legend!!)
        }

        private fun setData(entries: List<Entry>?, label: String) {

            val set1: LineDataSet

            // create a dataset and give it a type
            val values = ArrayList<Entry>()
            values.add(Entry(0F, 220F))

            set1 = if(entries!!.isEmpty()) LineDataSet(values, label) else LineDataSet(entries, label)
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
            set1.fillColor = resouces.getColor(R.color.startColor)

            set1.fillFormatter = IFillFormatter { dataSet, dataProvider ->
                binding.measureChart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            updateData(data)

        }

        private fun updateData(data: LineData){
            binding.measureChart.notifyDataSetChanged()
            binding.measureChart.data = data
            binding.measureChart.setVisibleXRangeMaximum(30F)
            binding.measureChart.moveViewToX(data.entryCount.toFloat())
            binding.measureChart.invalidate()
        }
        
    }

}