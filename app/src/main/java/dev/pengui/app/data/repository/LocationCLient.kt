package dev.pengui.app.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface LocationClient {
    suspend fun getCurrentLocation(): Location
}

class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationClient {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location {
        return suspendCancellableCoroutine { cont ->
            client.lastLocation.apply {
                addOnSuccessListener { location ->
                    location?.let { cont.resume(location) }
                }
                addOnFailureListener { e ->
                    cont.resumeWithException(e)
                }
            }
        }
    }
}