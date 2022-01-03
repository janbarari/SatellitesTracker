package io.github.janbarari.satellitestracker.core.extensions

import android.os.Parcelable
import androidx.fragment.app.Fragment

inline infix fun <reified T> Fragment.arg(key: String): Lazy<T> {
    return lazy {
        if (T::class is Parcelable) {
            arguments!!.getParcelable(key)!!
        } else {
            arguments!!.get(key) as T
        }
    }
}