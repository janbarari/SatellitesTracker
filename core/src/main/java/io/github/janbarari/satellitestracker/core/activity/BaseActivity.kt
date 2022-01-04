package io.github.janbarari.satellitestracker.core.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(@LayoutRes private val layoutId: Int): AppCompatActivity() {

    abstract fun getLayoutBinding(v: View): VB

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityCreate(savedInstanceState, intent)
        binding = getLayoutBinding(LayoutInflater.from(this).inflate(layoutId, null))
        setContentView(binding.root)
        initViews()
        observers()
        listeners()
        onActivityCreated()
    }

    open fun onActivityCreate(savedInstanceState: Bundle?, intent: Intent) {}
    open fun initViews(){}
    open fun observers(){}
    open fun listeners(){}
    open fun onActivityCreated(){}

}