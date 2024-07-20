package com.example.weatherapp.ui.support

import com.example.weatherapp.ui.viewmodel.BaseViewModel

interface HasBaseViewModel<OuterEvent : Any> {

    fun provideViewModel(): BaseViewModel<OuterEvent>?
}