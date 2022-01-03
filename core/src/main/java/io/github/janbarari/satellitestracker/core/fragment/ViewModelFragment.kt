package io.github.janbarari.satellitestracker.core.fragment

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import io.github.janbarari.satellitestracker.core.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class ViewModelFragment<VB: ViewBinding, VM: ViewModel>(
    @LayoutRes layoutId: Int,
    private val viewModelClass: Class<VM>
): BaseFragment<VB>(layoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VM>
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }

}