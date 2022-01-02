package io.github.janbarari.satellitestracker.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    abstract fun getBindingLayout(v: View): VB
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentCreate(savedInstanceState, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBindingLayout(LayoutInflater.from(requireContext()).inflate(layoutId, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views()
        observers()
        listeners()
        onFragmentCreated()
    }

    open fun onFragmentCreate(savedInstanceState: Bundle?, arguments: Bundle?) {}
    open fun views() {}
    open fun observers() {}
    open fun listeners() {}
    open fun onFragmentCreated() {}

}