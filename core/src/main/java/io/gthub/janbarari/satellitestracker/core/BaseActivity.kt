package io.gthub.janbarari.satellitestracker.core

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
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
        views()
        observers()
        listeners()
        onActivityCreated()
    }

    open fun onActivityCreate(savedInstanceState: Bundle?, intent: Intent) {}
    open fun views(){}
    open fun observers(){}
    open fun listeners(){}
    open fun onActivityCreated(){}

}