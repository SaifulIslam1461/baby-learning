package com.kids.learningvideo.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kids.learningvideo.data.MediaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGalleryScreen(
    photos: List<MediaEntity>,
    onBack: () -> Unit
) {
    val photoList = photos.filter { it.type == "PHOTO" }
    var selectedPhoto by remember { mutableStateOf<MediaEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ফটো গ্যালারি (Photos)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (photoList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("কোনো ছবি পাওয়া যায়নি! দয়া করে যোগ করুন।", color = Color.Gray)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(photoList) { photo ->
                    Card(
                        modifier = Modifier
                            .height(180.dp)
                            .clickable { selectedPhoto = photo },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = Uri.parse(photo.uriString),
                                contentDescription = photo.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(4.dp)
                            ) {
                                Text(text = photo.title, color = Color.White, maxLines = 1)
                            }
                        }
                    }
                }
            }
        }

        // Fullscreen Photo View Dialog
        selectedPhoto?.let { photo ->
            AlertDialog(
                onDismissRequest = { selectedPhoto = null },
                confirmButton = {
                    TextButton(onClick = { selectedPhoto = null }) { Text("বন্ধ করুন") }
                },
                title = { Text(photo.title) },
                text = {
                    AsyncImage(
                        model = Uri.parse(photo.uriString),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
            )
        }
    }
}
