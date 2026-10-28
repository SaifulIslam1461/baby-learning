package com.kids.learningvideo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kids.learningvideo.data.MediaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    mediaList: List<MediaEntity>,
    onDeleteMedia: (MediaEntity) -> Unit,
    onBack: () -> Unit
) {
    var itemToDelete by remember { mutableStateOf<MediaEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("সেটিংস ও ফাইল ম্যানেজমেন্ট") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text("সংরক্ষিত ফাইলসমূহ ডিলিট বা পরিবর্তন করুন:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(mediaList) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    ListItem(
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text("ক্যাটাগরি: ${item.category} | ধরণ: ${item.type}") },
                        trailingContent = {
                            IconButton(onClick = { itemToDelete = item }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    )
                }
            }
        }

        // Delete Confirmation Dialog
        itemToDelete?.let { item ->
            AlertDialog(
                onDismissRequest = { itemToDelete = null },
                title = { Text("ফাইল মুছে ফেলা") },
                text = { Text("আপনি কি নিশ্চিতভাবে \"${item.title}\" ফাইলটি মুছে ফেলতে চান?") },
                confirmButton = {
                    TextButton(onClick = {
                        onDeleteMedia(item)
                        itemToDelete = null
                    }) {
                        Text("হ্যাঁ, ডিলিট করুন", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { itemToDelete = null }) { Text("বাতিল") }
                }
            )
        }
    }
}
