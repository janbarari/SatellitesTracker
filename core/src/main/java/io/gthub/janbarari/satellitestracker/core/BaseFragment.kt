package io.gthub.janbarari.satellitestracker.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>(
    @LayoutRes private val layoutId: Int
): Fragment() {

    abstract fun getBindingLayout(v: View): VB
    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBindingLayout(LayoutInflater.from(requireContext()).inflate(layoutId, null))
        return binding.root
    }

}