package dev.pengui.app.data.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(private val context: Context) {

    suspend fun requestCameraPermission(
        context: Context,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onGranted()
        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
    }

        suspend fun checkAndRequestCameraPermission(): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }

        suspend fun checkAndRequestStoragePermission(): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermission(activity: Activity, permission: String) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                when (permission) {
                    Manifest.permission.CAMERA -> CAMERA_REQUEST_CODE
                    else -> STORAGE_REQUEST_CODE
                }
            )
        }

        companion object {
            const val CAMERA_REQUEST_CODE = 1001
            const val STORAGE_REQUEST_CODE = 1002
        }
    }
