package io.github.janbarari.satellitestracker.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

class ViewModelFactory<VM : ViewModel> @Inject constructor(private val viewModel: Lazy<VM>) : ViewModelProvider.Factory {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return viewModel.get() as VM
    }
}
