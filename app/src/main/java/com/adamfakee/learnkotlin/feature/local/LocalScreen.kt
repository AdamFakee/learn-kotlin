package com.adamfakee.learnkotlin.feature.local

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamfakee.learnkotlin.feature.local.data.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalScreen(viewModel: LocalViewModel = hiltViewModel()) {
    val todos by viewModel.todos.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }
    var todoToDelete by remember { mutableStateOf<TodoEntity?>(null) }
    var todoToEdit by remember { mutableStateOf<TodoEntity?>(null) }

    Scaffold(topBar = { TopAppBar(title = { Text("Nhiệm vụ (Room DB)") }) }) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            // Phần Thêm mới
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("Thêm việc cần làm...") },
                        modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                        onClick = {
                            viewModel.addTodo(newTaskTitle)
                            newTaskTitle = ""
                        }
                ) { Icon(Icons.Default.Add, contentDescription = "Thêm") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Danh sách
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(todos, key = { it.id }) { todo ->
                    TodoItem(
                            todo = todo,
                            onCheckedChange = { isChecked ->
                                viewModel.updateTodo(todo.copy(isCompleted = isChecked))
                            },
                            onEditClick = { todoToEdit = todo },
                            onDeleteClick = { todoToDelete = todo }
                    )
                }
            }
        }

        // --- Dialogs ---

        // 1. Popup Xác nhận Xóa
        todoToDelete?.let { todo ->
            AlertDialog(
                    onDismissRequest = { todoToDelete = null },
                    title = { Text("Xác nhận xóa") },
                    text = { Text("Bạn có chắc chắn muốn xóa nhiệm vụ '${todo.title}' không?") },
                    confirmButton = {
                        TextButton(
                                onClick = {
                                    viewModel.deleteTodo(todo)
                                    todoToDelete = null
                                }
                        ) { Text("Xóa", color = MaterialTheme.colorScheme.error) }
                    },
                    dismissButton = {
                        TextButton(onClick = { todoToDelete = null }) { Text("Hủy") }
                    }
            )
        }

        // 2. Popup Chỉnh sửa
        todoToEdit?.let { todo ->
            var editTitle by remember { mutableStateOf(todo.title) }
            AlertDialog(
                    onDismissRequest = { todoToEdit = null },
                    title = { Text("Sửa nhiệm vụ") },
                    text = {
                        OutlinedTextField(
                                value = editTitle,
                                onValueChange = { editTitle = it },
                                modifier = Modifier.fillMaxWidth()
                        )
                    },
                    confirmButton = {
                        TextButton(
                                onClick = {
                                    viewModel.updateTodo(todo.copy(title = editTitle))
                                    todoToEdit = null
                                }
                        ) { Text("Lưu") }
                    },
                    dismissButton = { TextButton(onClick = { todoToEdit = null }) { Text("Hủy") } }
            )
        }
    }
}

@Composable
fun TodoItem(
        todo: TodoEntity,
        onCheckedChange: (Boolean) -> Unit,
        onEditClick: () -> Unit,
        onDeleteClick: () -> Unit
) {
    Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            colors =
                    CardDefaults.cardColors(
                            containerColor =
                                    if (todo.isCompleted) MaterialTheme.colorScheme.surfaceVariant
                                    else MaterialTheme.colorScheme.surface
                    ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = todo.isCompleted, onCheckedChange = onCheckedChange)
            Text(
                    text = todo.title,
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    color =
                            if (todo.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant
                            else MaterialTheme.colorScheme.onSurface,
                    style =
                            if (todo.isCompleted)
                                    MaterialTheme.typography.bodyLarge.copy(
                                            textDecoration = TextDecoration.LineThrough
                                    )
                            else MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Sửa")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                        Icons.Default.Delete,
                        contentDescription = "Xóa",
                        tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
