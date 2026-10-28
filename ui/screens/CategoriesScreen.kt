package com.kids.learningvideo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kids.learningvideo.data.MediaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    mediaList: List<MediaEntity>,
    onBack: () -> Unit
) {
    val categories = listOf(
        "English Learning",
        "Arabic Learning",
        "বাংলা শিক্ষা",
        "গণিত শিক্ষা",
        "ইসলামিক শিক্ষা",
        "অন্যান্য"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ক্যাটাগরি সমূহ") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                val count = mediaList.count { it.category == category }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    ListItem(
                        headlineContent = { Text(category, style = MaterialTheme.typography.titleMedium) },
                        trailingContent = { Text("$count টি ফাইল", style = MaterialTheme.typography.bodyMedium) }
                    )
                }
            }
        }
    }
}
