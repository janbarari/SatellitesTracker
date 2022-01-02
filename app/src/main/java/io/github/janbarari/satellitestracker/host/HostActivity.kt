package io.github.janbarari.satellitestracker.host

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.R
import io.github.janbarari.satellitestracker.databinding.HostActivityBinding
import io.github.janbarari.satellitestracker.core.ViewModelActivity

@AndroidEntryPoint
class HostActivity : ViewModelActivity<HostActivityBinding, HostViewModel>(
    R.layout.host_activity,
    HostViewModel::class.java
) {
    override fun getLayoutBinding(v: View) = HostActivityBinding.bind(v)

    override fun views() {


    }
}