package com.example.adhdappprototype.ui.add_edit_comprehensive_todo

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
import com.example.adhdappprototype.util.UiEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditComprehensiveTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditComprehensiveTodoViewModel = hiltViewModel()
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
                viewModel.onEvent(AddEditComprehensiveTodoEvent.OnSaveTodoClick)
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
                    viewModel.onEvent(AddEditComprehensiveTodoEvent.OnTitleChange(it))
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
                    viewModel.onEvent(AddEditComprehensiveTodoEvent.OnDescriptionChange(it))
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
                                viewModel.onEvent(AddEditComprehensiveTodoEvent.OnTagChange(selectionOption))
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
                                viewModel.onEvent(AddEditComprehensiveTodoEvent.OnPriorityChange(selectionOption))
                                expandedPriority = false
                            }
                        ) {
                            Text(text = selectionOption.string)
                        }
                    }
                }
            }
        }
    }
}