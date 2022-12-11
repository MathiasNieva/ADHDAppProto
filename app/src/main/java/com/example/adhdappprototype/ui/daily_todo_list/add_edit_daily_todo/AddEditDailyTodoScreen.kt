package com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoViewModel
import com.example.adhdappprototype.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditDailyTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditDailyTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    var expandedTag by remember { mutableStateOf(false) }
    var expandedPriority by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditDailyTodoEvent.OnSaveTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditDailyTodoEvent.OnTitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(AddEditDailyTodoEvent.OnDescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expandedTag,
                onExpandedChange = {
                    expandedTag = !expandedTag
                }
            ) {
                viewModel.tag.let { it1 ->
                    if (it1 != null) {
                        TextField(
                            readOnly = true,
                            value = "TAG:  " + it1.string,
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedTag
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                    }
                    else if (it1 == null) {
                        TextField(
                            readOnly = true,
                            value = "",
                            onValueChange = { },
                            placeholder = {
                                Text(text = "Tag")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedTag
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                    }
                }
                ExposedDropdownMenu(
                    expanded = expandedTag,
                    onDismissRequest = {
                        expandedTag = false
                    }
                ) {
                    enumValues<Tag>().forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(AddEditDailyTodoEvent.OnTagChange(selectionOption))
                                expandedTag = false
                            }
                        ) {
                            Text(text = selectionOption.string)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expandedPriority,
                onExpandedChange = {
                    expandedPriority = !expandedPriority
                }
            ) {
                viewModel.priority.let { it1 ->
                    if (it1 != null) {
                        TextField(
                            readOnly = true,
                            value = "PRIORITY:  " + it1.string,
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedPriority
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                    }
                    else if (it1 == null) {
                        TextField(
                            readOnly = true,
                            value = "",
                            onValueChange = { },
                            placeholder = {
                                Text(text = "Priority")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedPriority
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                    }
                }
                ExposedDropdownMenu(
                    expanded = expandedPriority,
                    onDismissRequest = {
                        expandedPriority = false
                    }
                ) {
                    enumValues<Priority>().forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(AddEditDailyTodoEvent.OnPriorityChange(selectionOption))
                                expandedPriority = false
                            }
                        ) {
                            Text(text = selectionOption.string)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.timeFrame,
                onValueChange = {
                    viewModel.onEvent(AddEditDailyTodoEvent.OnTimeFrameChange(it))
                },
                placeholder = {
                    Text(text = "Estimated time")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.ifThenPlan,
                onValueChange = {
                    viewModel.onEvent(AddEditDailyTodoEvent.OnIfThenPlanChange(it))
                },
                placeholder = {
                    Text(text = "IF-THEN plan")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}