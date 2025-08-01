package dev.pengui.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.pengui.app.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = viewModel()
    //val menuItems by viewModel.menuItems.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        //AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

//        MenuGrid(
//            items = menuItems,
//            onItemClick = { destination ->
//                navController.navigate(destination.route)
//            }
//        )

        Spacer(modifier = Modifier.height(16.dp))

        //WeatherInfo()
    }
}

// presentation/component/MenuGrid.kt
@Composable
fun MenuGrid(
    //items: List<MenuItem>,
    //onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = modifier.weight(1f)
//    ) {
//        items(items) { item ->
//            MenuCard(
//                title = item.title,
//                iconRes = item.iconRes,
//                onClick = { onItemClick(item) }
//            )
//        }
//    }
}