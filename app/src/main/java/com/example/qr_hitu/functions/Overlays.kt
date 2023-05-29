package com.example.qr_hitu.functions

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qr_hitu.ViewModels.MalfunctionViewModel
import com.example.qr_hitu.ViewModels.ScannerViewModel
import com.example.qr_hitu.ViewModels.ViewModel1
import com.example.qr_hitu.ViewModels.ViewModel2
import com.example.qr_hitu.components.*
import com.example.qr_hitu.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@Composable
fun ScaffoldLayouts(navController: NavController, viewModel1: ViewModel1, viewModel2: ViewModel2, viewModelSA : ScannerViewModel, viewModelMF : MalfunctionViewModel){

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val destinationRoute = currentDestination?.route ?: Login.route

    Scaffold(
        topBar = {
            when {
                destinationRoute.contains(ScannerAdminInfoUpdate.route) ->  TopBar1(navController = navController)
                destinationRoute.contains(Create1.route) -> TopBar5(navController = navController)
                destinationRoute.contains(Create2.route) -> TopBar2(navController = navController)
                destinationRoute.contains(Create3.route) -> TopBar3(navController = navController)
                destinationRoute.contains(ScanInput.route) -> TopBarUser3(navController = navController)
                destinationRoute.contains(SettingOptions.route) || destinationRoute.contains(Manual.route) || destinationRoute.contains(MalfInfo.route) -> TopBarUni(navController = navController)
                destinationRoute.contains(ScannerAdminInfo.route) -> TopBar4(navController = navController, viewModelSA)
                destinationRoute.contains(MalfList.route) || destinationRoute.contains(ScanAdmin.route) || destinationRoute.contains(AdminChoices.route) -> TopBar1(navController = navController)
                destinationRoute.contains(UserChoices.route) -> TopBarUser1(navController = navController)
                destinationRoute.contains(ScanProf.route) || destinationRoute.contains(MQRLocal.route) -> TopBarUser2(navController = navController)
            }
        },
        bottomBar = {
            when{
                destinationRoute.contains(MalfList.route) || destinationRoute.contains(ScanAdmin.route) || destinationRoute.contains(AdminChoices.route) -> BottomBar(navController = navController)
            }
        },
        scaffoldState = scaffoldState,
    ) { innerPadding ->
            QrHituNavHost(
                navController = navController,
                viewModel1 = viewModel1,
                viewModel2 = viewModel2,
                viewModelSA = viewModelSA,
                viewModelMF = viewModelMF,
                modifier = Modifier.padding(innerPadding)
            )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar1(navController: NavController){
        TopAppBar(
                title = { Text(text = "Admin", color = md_theme_light_onPrimaryContainer) },
                colors = topAppBarColors(md_theme_light_primaryContainer),
                navigationIcon = {
                    MenuOptions(navController = navController)
                }
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar2(navController: NavController){

    TopAppBar(
        title = { Text(text = "Admin", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack ,"Back", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar3(navController: NavController){

    TopAppBar(
        title = { Text(text = "Admin", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(MalfList.route) }) {
                Icon(Icons.Filled.Close ,"Close", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar4(navController: NavController, viewModel: ScannerViewModel){

    val showState = remember { mutableStateOf(false) }
    val show by rememberUpdatedState(showState.value)
    val (block, room, machine) = viewModel.myData.value.toString().split(",")

    TopAppBar(
        title = { Text(text = "Admin", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            MenuOptions(navController = navController)
        },
        actions = {

            IconButton(onClick = { navController.navigate(ScannerAdminInfoUpdate.route) }) {
                Icon(Icons.Filled.Edit, "Edit", tint = md_theme_light_onPrimaryContainer)
            }
            IconButton(onClick = { showState.value = true }) {
                Icon(Icons.Filled.Delete, "Delete", tint = md_theme_light_onPrimaryContainer)
            }
        },
    )
    if(show){
        DelDialog(onDialogDismissed = { showState.value = false}, onDeleteClick = { delDispositivo(block, room, machine); navController.navigate(MalfList.route) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar5(navController: NavController){
    TopAppBar(
        title = { Text(text = "Admin", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            MenuOptions(navController = navController)
        },
        actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack ,"Back", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser1(navController: NavController){

    TopAppBar(
        title = { Text(text = "*Nome do Professor*", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
           MenuOptions(navController = navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser2(navController: NavController){

    TopAppBar(
        title = { Text(text = "*Nome do Professor*", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            MenuOptions(navController = navController)
        },
        actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack ,"Back", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser3(navController: NavController){
    TopAppBar(
        title = { Text(text = "*Nome do Professor*", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            MenuOptions(navController = navController)
        },
        actions = {
            IconButton(onClick = { navController.navigate(UserChoices.route) }) {
                Icon(Icons.Filled.Close ,"Close", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUni(navController: NavController){

    TopAppBar(
        title = { Text(text = "", color = md_theme_light_onPrimaryContainer) },
        colors = topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack ,"Back", tint = md_theme_light_onPrimaryContainer)
            }
        }
    )

}


@Composable
fun BottomBar(navController: NavController){

            BottomAppBar(
                containerColor = md_theme_light_primaryContainer
            ) {
                NavigationBarItem(
                    selected = navController.currentBackStackEntry?.destination?.route == MalfList.route,
                    label = { Text(text = "Avarias", color = md_theme_light_onPrimaryContainer) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor= md_theme_light_onPrimaryContainer
                    ),
                    onClick = { navController.navigate(MalfList.route) },
                    icon = {
                        Icon(Icons.Filled.FormatListBulleted, "Avarias")
                    }
                )
                NavigationBarItem(
                    selected =
                            navController.currentBackStackEntry?.destination?.route == ScanAdmin.route ||
                                navController.currentBackStackEntry?.destination?.route == ScannerAdminInfo.route ||
                                    navController.currentBackStackEntry?.destination?.route == ScannerAdminInfoUpdate.route,
                    label = { Text(text = "Scanner", color = md_theme_light_onPrimaryContainer) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor= md_theme_light_onPrimaryContainer
                    ),
                    onClick = { navController.navigate(ScanAdmin.route) },
                    icon = {
                        Icon(Icons.Filled.QrCodeScanner, "Scanner")
                    }
                )
                NavigationBarItem(
                    selected = navController.currentBackStackEntry?.destination?.route == AdminChoices.route,
                    label = { Text(text = "Create", color = md_theme_light_onPrimaryContainer) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor= md_theme_light_onPrimaryContainer
                    ),
                    onClick = { navController.navigate(AdminChoices.route) },
                    icon = {
                        Icon(Icons.Filled.QrCode2, "Create")
                    }
                )
            }
}

@Composable
fun MenuOptions(navController: NavController){

    var showMenu by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(Icons.Filled.Menu ,"Menu", tint = md_theme_light_onPrimaryContainer)
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text(text = "Definições") },
            onClick = {  navController.navigate(SettingOptions.route) },
            leadingIcon = {
                Icon(Icons.Filled.Settings, "Settings")
            }
        )
        DropdownMenuItem(
            text = { Text(text = "Manual") },
            onClick = { navController.navigate(Manual.route) },
            leadingIcon = {
                Icon(Icons.Filled.Book, "Manual")
            }
        )
        DropdownMenuItem(
            text = { Text(text = "Sair Sessão") },
            onClick = {
                scope.launch {
                    Firebase.auth.signOut()
                    navController.navigate(Login.route)
                }
            },
            leadingIcon = {
                Icon(Icons.Filled.Logout, "Logout")
            }
        )
    }
}

@Composable
fun DelDialog(onDialogDismissed: () -> Unit, onDeleteClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDialogDismissed() },
        title = {
            Text(
                text = "Deseja apagar está máquina ?",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(text = "Esta ação é irreversível!", style = MaterialTheme.typography.bodyLarge)
        },
        dismissButton = {
            TextButton(onClick = { onDialogDismissed() }) {
                Text(
                    text = "Não",
                    style = MaterialTheme.typography.labelLarge,
                    color = md_theme_light_primary
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onDialogDismissed(); onDeleteClick() }) {
                Text(
                    text = "Sim",
                    style = MaterialTheme.typography.labelLarge,
                    color = md_theme_light_primary
                )
            }
        },
        textContentColor = md_theme_light_primaryContainer,
        titleContentColor = md_theme_light_primary
    )
}