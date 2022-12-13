package com.example.adhdappprototype.ui.comprehensive_todo_list

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.adhdappprototype.BottomNavigationBar
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.ui.BottomNavItem
import com.example.adhdappprototype.util.Routes
import com.example.adhdappprototype.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComprehensiveTodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ComprehensiveTodoListViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(ComprehensiveTodoListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ComprehensiveTodoListEvent.OnAddTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
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
                        route = "settings",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {
            items(todos.value) { todo ->
                ComprehensiveTodoItem(
                    todo = todo,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(ComprehensiveTodoListEvent.OnTodoClick(todo))
                        }
                        .drawBehind {
                            val borderSize = 1.dp.toPx()
                            val y = size.height + borderSize / 2
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = borderSize
                            )
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}