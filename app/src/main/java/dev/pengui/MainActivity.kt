package dev.pengui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.pengui.app.domain.model.Screen
import dev.pengui.app.presentation.screen.HomeScreen
import dev.pengui.app.presentation.state.MainUiState
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import dev.pengui.app.presentation.viewmodel.MainViewModel
import dev.pengui.ui.theme.AgroHeroTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgroHeroTheme {
                AppMain(
                    viewModel = viewModel,
                    onPermissionRequest = {
                        requestPermissionsIfNeeded()
                    }
                )
            }
        }
    }

    private fun requestPermissionsIfNeeded() {
        val cameraGranted = ContextCompat.checkSelfPermission(
            this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        val locationGranted = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (cameraGranted && locationGranted) {
            viewModel.onPermissionGranted()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),PERMISSION_REQUEST_CODE

            )
        }
    }

    /*private fun requestPermission() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        } else {
            viewModel.onPermissionGranted()
        }
    }*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.onPermissionGranted()
                } else {
                    viewModel.onPermissionDenied()
                }
            }
            PERMISSION_REQUEST_CODE -> {
                val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
                if (allGranted) {
                    viewModel.onPermissionGranted()
                } else {
                    viewModel.onPermissionDenied()
                }
            }
        }
    }

    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 1001
        const val PERMISSION_REQUEST_CODE = 1000
    }
}

@Composable
fun AppMain(
    viewModel: MainViewModel,
    onPermissionRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        onPermissionRequest()
    }
    /*LaunchedEffect(uiState) {
        when (uiState) {
            is MainUiState.PermissionDenied -> {

            }
            is MainUiState.PermissionGranted -> {

            }
            else -> {}
        }
    }*/

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(padding),
            uiState = uiState,
            onPermissionRequest = onPermissionRequest
        )
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    uiState: MainUiState,
    onPermissionRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val vm = koinViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                modifier = modifier
            )
        }
//        composable(Screen.Shop.route) {
//            ShopScreen()
//        }
    }
}
