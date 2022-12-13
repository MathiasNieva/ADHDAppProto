package com.example.adhdappprototype.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.adhdappprototype.BottomNavigationBar
import com.example.adhdappprototype.ui.BottomNavItem
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoItem
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent
import com.example.adhdappprototype.util.Routes
import com.example.adhdappprototype.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val settings = viewModel.settings.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Comprehensive Todo",
                        route = Routes.COMPREHENSIVE_TODO_LIST,
                        icon = Icons.Default.Check
                    ),
                    BottomNavItem(
                        name = "Daily Todo",
                        route = Routes.DAILY_TODO_LIST,
                        icon = Icons.Default.CheckCircle
                    ),
                    BottomNavItem(
                        name = "Daily Planner",
                        route = Routes.DAILY_PLANNER,
                        icon = Icons.Default.DateRange
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = Routes.SETTINGS,
                        icon = Icons.Default.Settings
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Column(

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SETTINGS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(SettingsEvent.OnSettingClick)
                        }
                        .border(1.dp, color = Color.DarkGray)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Set time to plan",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}