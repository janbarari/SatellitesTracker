@file:Suppress("BlockingMethodInNonBlockingContext")

package io.github.janbarari.satellitestracker.core.extensions

import androidx.annotation.CheckResult
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

data class SearchViewQueryTextEvent(
    val view: SearchView,
    val query: CharSequence,
    val isSubmitted: Boolean,
)

@ExperimentalCoroutinesApi
@CheckResult
@Suppress("BlockingMethodInNonBlockingContext")
fun SearchView.queryTextEvents(): Flow<SearchViewQueryTextEvent> {
    return callbackFlow {
        checkMainThread()
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                trySendBlocking(
                    SearchViewQueryTextEvent(
                        view = this@queryTextEvents,
                        query = query,
                        isSubmitted = true,
                    )
                )
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                trySendBlocking(
                    SearchViewQueryTextEvent(
                        view = this@queryTextEvents,
                        query = newText,
                        isSubmitted = false,
                    )
                )
                return true
            }
        })

        awaitClose { setOnQueryTextListener(null) }
    }.onStart {
        emit(
            SearchViewQueryTextEvent(
                view = this@queryTextEvents,
                query = query,
                isSubmitted = false,
            )
        )
    }
}
