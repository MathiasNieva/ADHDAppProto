package com.example.adhdappprototype.ui.daily_planner

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.ui.components.TagContainer
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent

@Composable
fun DailyPlanItem(
    todo: DailyPlan,
    onEvent: (DailyPlannerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.drawBehind {
                val borderSize = 1.dp.toPx()
                val x = size.width + borderSize / 2
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(x, -29.dp.toPx()),
                    end = Offset(x, 52.dp.toPx()),
                    strokeWidth = borderSize
                )
                }
                .padding(end = 10.dp)
        ) {
            Text(
                text = todo.time
            )
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 10.dp),
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
                    onEvent(DailyPlannerEvent.OnDeleteTodoClick(todo))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
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
                onEvent(DailyPlannerEvent.OnDoneChange(todo, isChecked))
            }
        )
    }
}