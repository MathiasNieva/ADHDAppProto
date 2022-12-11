package com.example.adhdappprototype.ui.comprehensive_todo_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Dp.Companion.Hairline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.ui.components.TagContainer

@Composable
fun ComprehensiveTodoItem(
    todo: ComprehensiveTodo,
    onEvent: (ComprehensiveTodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(ComprehensiveTodoListEvent.OnDeleteTodoClick(todo))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            todo.description?.let {
                if (todo.description != "") {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it)
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            todo.tag?.let {
                TagContainer(string = it.string, surfaceColor = Color.Magenta, textColor = Color.White)
            }

            todo.priority?.let {
                Spacer(modifier = Modifier.height(8.dp))
                TagContainer(string = it.string, surfaceColor = it.color, textColor = Color.DarkGray)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { isChecked ->
                onEvent(ComprehensiveTodoListEvent.OnDoneChange(todo, isChecked))
            }
        )
    }
}