package com.eslirodrigues.tutorials.network_state.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TutorialNetworkManager @Inject constructor(@ApplicationContext context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun getNetworkState(): Flow<TutorialNetworkState> = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(TutorialNetworkState.Available )
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(TutorialNetworkState.Losing)
            }

            override fun onLost(network: Network) {
                trySend(TutorialNetworkState.Lost)
            }

            override fun onUnavailable() {
                trySend(TutorialNetworkState.Unavailable)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_MMS)) {
                    trySend(TutorialNetworkState.Available)
                }
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.flowOn(Dispatchers.IO)
}