package com.example.adhdappprototype.ui.daily_planner.add_edit_plan

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent
import com.example.adhdappprototype.util.UiEvent
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditPlanScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditPlanViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    var expandedTag by remember { mutableStateOf(false) }
    var expandedPriority by remember { mutableStateOf(false) }

    var pickedTimeStart by remember {
        mutableStateOf(LocalTime.NOON)
    }
    var pickedTimeEnd by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedTimeStart by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm")
                .format(pickedTimeStart)
        }
    }
    val formattedTimeEnd by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm")
                .format(pickedTimeEnd)
        }
    }

    val timeStartDialogState = rememberMaterialDialogState()
    val timeEndDialogState = rememberMaterialDialogState()

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
                viewModel.onEvent(AddEditPlanEvent.OnSaveTodoClick)
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
                    viewModel.onEvent(AddEditPlanEvent.OnTitleChange(it))
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
                    viewModel.onEvent(AddEditPlanEvent.OnDescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            // Time picker ----------------------------------------------------------------
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { timeStartDialogState.show() }
                ) {
                    Text(text = "Set up time frame")
                }
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = viewModel.time
                )
            }
            MaterialDialog(
                dialogState = timeStartDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        timeEndDialogState.show()
                    }
                    negativeButton(text = "Cancel")
                }
            ) {
                timepicker(
                    initialTime = LocalTime.NOON,
                    title = "Pick start time",
                    is24HourClock = true
                ) {
                    pickedTimeStart = it
                }
            }
            MaterialDialog(
                dialogState = timeEndDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        viewModel.onEvent(AddEditPlanEvent.OnTimeChange("$formattedTimeStart-$formattedTimeEnd"))
                    }
                    negativeButton(text = "Cancel")
                }
            ) {
                timepicker(
                    initialTime = pickedTimeStart,
                    title = "Pick end time",
                    is24HourClock = true,
                    timeRange = pickedTimeStart..LocalTime.MAX
                ) {
                    pickedTimeEnd  = it
                }
            }
            // Dropdown menu for tag -----------------------------------------------
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
                                viewModel.onEvent(AddEditPlanEvent.OnTagChange(selectionOption))
                                expandedTag = false
                            }
                        ) {
                            Text(text = selectionOption.string)
                        }
                    }
                }
            }
            // Dropdown menu for priority--------------------------------------------------
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
                                viewModel.onEvent(AddEditPlanEvent.OnPriorityChange(selectionOption))
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