package com.nicomahnic.enerfiv2.ui.mainFragments.home

import androidx.hilt.lifecycle.ViewModelInject
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel

class HomeVM @ViewModelInject constructor(

) : BaseViewModel<HomeDataState, HomeAction, HomeEvent>(){

    init {
        viewState = HomeDataState(
            state = HomeState.Initial
        )
    }

    override fun process(viewEvent: HomeEvent) {
        super.process(viewEvent)
        when (viewEvent){

        }
    }

}