package com.nicomahnic.enerfiv2.ui.mainFragments.measure

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentMeasureBinding
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MeasureFragment : BaseFragment<MeasureDataState, MeasureAction, MeasureEvent, MeasureVM>
    (R.layout.fragment_measure)
{
    private val args: MeasureFragmentArgs by navArgs()
    override val viewModel: MeasureVM by viewModels()
    private lateinit var binding: FragmentMeasureBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMeasureBinding.bind(view)
        v = view

        viewModel.process(MeasureEvent.LoadData(args.mail, args.passwd, args.mac))
        binding.fabRefresh.setOnClickListener {
            viewModel.process(MeasureEvent.LoadData(args.mail, args.passwd, args.mac))
        }
    }

    override fun renderViewState(viewState: MeasureDataState) {
        when (viewState.state) {
            is MeasureState.Plot -> {
                binding.edtCosPhi.setText(viewState.cosPhi)
                binding.edtThd.setText(viewState.thd)
                binding.edtPf.setText(viewState.powerFactor)
                binding.constraintLayout2.visibility = View.VISIBLE

                val charts = listOf(
                    MeasureChart(viewState.voltage, "V", "Voltage:", viewState.timeStamp),
                    MeasureChart(viewState.current, "A", "Current:", viewState.timeStamp),
                    MeasureChart(viewState.activePower, "W", "Power:", viewState.timeStamp),
                )
                val recyclerView = binding.rvMeasures
//                recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = MeasureAdapter(charts, resources)
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


}