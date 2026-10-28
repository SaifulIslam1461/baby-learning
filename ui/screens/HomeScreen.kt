package com.kids.learningvideo.ui.screens

 androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToVideo: () -> Unit,
    onNavigateToPhoto: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌟 Kids Learning Video 🌟", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "Add Media", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Big Option 1: Video Feed (TikTok Style)
            HomeMenuCard(
                title = "ভিডিও ফিড (Videos)",
                subtitle = "সোয়াইপ করে মজার শিক্ষণীয় ভিডিও দেখুন",
                icon = Icons.Default.PlayArrow,
                backgroundColor = Color(0xFFFF8B94),
                onClick = onNavigateToVideo
            )

            // Big Option 2: Photo Gallery
            HomeMenuCard(
                title = "ফটো গ্যালারি (Photos)",
                subtitle = "ছবির মাধ্যমে বর্ণমালা ও সংখ্যা শিখুন",
                icon = Icons.Default.Image,
                backgroundColor = Color(0xFFFFD3B6),
                onClick = onNavigateToPhoto
            )

            // Categories
            HomeMenuCard(
                title = "ক্যাটাগরি সমূহ (Categories)",
                subtitle = "ইংরেজি, আরবি, বাংলা, গণিত ও ইসলাম",
                icon = Icons.Default.Category,
                backgroundColor = Color(0xFFDCEDC1),
                onClick = onNavigateToCategories
            )

            // Settings & Backup
            HomeMenuCard(
                title = "সেটিংস ও স্টোরেজ (Settings)",
                subtitle = "স্টোরেজ ও অ্যাপ ম্যানেজমেন্ট",
                icon = Icons.Default.Settings,
                backgroundColor = Color(0xFFA8E6CF),
                onClick = onNavigateToSettings
            )
        }
    }
}

@Composable
fun HomeMenuCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.DarkGray)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subtitle, fontSize = 13.sp, color = Color.DarkGray.copy(alpha = 0.8f))
            }
        }
    }
}
