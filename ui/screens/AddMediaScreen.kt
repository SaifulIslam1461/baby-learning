package com.kids.learningvideo.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMediaScreen(
    onAddMedia: (title: String, category: String, type: String, uriString: String) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("বাংলা শিক্ষা") }
    var mediaType by remember { mutableStateOf("VIDEO") }
    var mediaUri by remember { mutableStateOf<Uri?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val categories = listOf("English Learning", "Arabic Learning", "বাংলা শিক্ষা", "গণিত শিক্ষা", "ইসলামিক শিক্ষা", "অন্যান্য")

    // Android Photo/Video Picker Launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            // Persist URI permission for long-term offline access
            val context = androidx.compose.ui.platform.LocalContext.current
            val flag = android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            try {
                context.contentResolver.takePersistableUriPermission(it, flag)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            mediaUri = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("নতুন ভিডিও বা ছবি যোগ করুন") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("ফাইল বা শেখার নাম (যেমন: ক-তে কলা)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Type Selector
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { mediaType = "VIDEO" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (mediaType == "VIDEO") MaterialTheme.colorScheme.primary else Color.LightGray)
                ) {
                    Text("ভিডিও (Video)")
                }
                Button(
                    onClick = { mediaType = "PHOTO" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (mediaType == "PHOTO") MaterialTheme.colorScheme.primary else Color.LightGray)
                ) {
                    Text("ছবি (Photo)")
                }
            }

            // Category Dropdown
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                    Text("ক্যাটাগরি: $selectedCategory")
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                selectedCategory = cat
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    val mimeType = if (mediaType == "VIDEO") "video/*" else "image/*"
                    launcher.launch(arrayOf(mimeType))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (mediaUri == null) "গ্যালারি থেকে ফাইল নির্বাচন করুন" else "ফাইল নির্বাচিত হয়েছে ✓")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (title.isNotBlank() && mediaUri != null) {
                        onAddMedia(title, selectedCategory, mediaType, mediaUri.toString())
                        onBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("সংরক্ষণ করুন (Save)", fontSize = 16.sp)
            }
        }
    }
}
