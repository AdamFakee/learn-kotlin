package com.adamfakee.learnkotlin.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkDetailScreen(
        viewModel: NetworkDetailViewModel = hiltViewModel(),
        onBackClick: () -> Unit
) {
    val comments by viewModel.comments.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text("Bình luận") },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Trở lại"
                                )
                            }
                        }
                )
            }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                items(comments) { comment ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                    text = comment.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                            )
                            Text(
                                    text = comment.email,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(text = comment.body, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
