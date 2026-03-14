package com.adamfakee.learnkotlin.feature.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamfakee.learnkotlin.feature.local.data.TodoDao
import com.adamfakee.learnkotlin.feature.local.data.TodoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class LocalViewModel @Inject constructor(private val todoDao: TodoDao) : ViewModel() {

    val todos: StateFlow<List<TodoEntity>> =
            todoDao.getAllTodos()
                    .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(5000),
                            initialValue = emptyList()
                    )

    fun addTodo(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch { todoDao.insertTodo(TodoEntity(title = title)) }
    }

    fun updateTodo(todo: TodoEntity) {
        viewModelScope.launch { todoDao.updateTodo(todo) }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch { todoDao.deleteTodo(todo) }
    }
}
