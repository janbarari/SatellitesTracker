package io.gthub.janbarari.satellitestracker.core

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class ViewModelFragment<VB: ViewBinding, VM: ViewModel>(
    @LayoutRes layoutId: Int,
    private val viewModelClass: Class<out ViewModel>
): BaseFragment<VB>(layoutId) {

    lateinit var viewModelFactory: ViewModelFactory<VM>
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }

}