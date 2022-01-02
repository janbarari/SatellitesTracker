package io.github.janbarari.satellitestracker.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<VM : ViewModel> constructor(private val viewModel: Lazy<VM>) : ViewModelProvider.Factory {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return viewModel.value as VM
    }
}
