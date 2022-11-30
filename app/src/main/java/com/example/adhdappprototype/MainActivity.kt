package com.example.adhdappprototype

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adhdappprototype.ui.BottomNavItem
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoScreen
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListScreen
import com.example.adhdappprototype.ui.daily_planner.DailyPlannerScreen
import com.example.adhdappprototype.ui.daily_planner.add_edit_plan.AddEditPlanScreen
import com.example.adhdappprototype.ui.daily_todo_list.DailyTodoListScreen
import com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo.AddEditDailyTodoScreen
import com.example.adhdappprototype.ui.theme.ADHDAppPrototypeTheme
import com.example.adhdappprototype.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADHDAppPrototypeTheme {
                val navController = rememberNavController()
                Scaffold(

                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.COMPREHENSIVE_TODO_LIST ) {
        composable(Routes.COMPREHENSIVE_TODO_LIST) {
            ComprehensiveTodoListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                navController = navController
            )
        }
        composable(
            route = Routes.ADD_EDIT_COMPREHENSIVE_TODO + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditComprehensiveTodoScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
        composable(Routes.DAILY_TODO_LIST) {
            DailyTodoListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                navController = navController
            )
        }
        composable(
            route = Routes.ADD_EDIT_DAILY_TODO + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditDailyTodoScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
        composable(Routes.DAILY_PLANNER) {
            DailyPlannerScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                navController = navController
            )
        }
        composable(
            route = Routes.ADD_EDIT_DAILY_PLAN + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditPlanScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Magenta,
                unselectedContentColor = Color.Blue,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(item.icon, contentDescription = item.name)
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ADHDAppPrototypeTheme {
        Greeting("Android")
    }
}