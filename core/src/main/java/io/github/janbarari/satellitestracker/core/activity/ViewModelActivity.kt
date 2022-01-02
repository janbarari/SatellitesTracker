package io.github.janbarari.satellitestracker.core.activity

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import androidx.lifecycle.ViewModelProvider
import io.github.janbarari.satellitestracker.core.viewmodel.ViewModelFactory

abstract class ViewModelActivity<VB : ViewBinding, VM : ViewModel>(
    @LayoutRes layoutId: Int,
    private val viewModelClass: Class<out ViewModel>
) : BaseActivity<VB>(layoutId) {

    lateinit var viewModelFactory: ViewModelFactory<VM>
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }

}