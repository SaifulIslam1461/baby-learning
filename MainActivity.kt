package com.kids.learningvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kids.learningvideo.ui.screens.*
import com.kids.learningvideo.ui.theme.KidsLearningVideoTheme
import com.kids.learningvideo.viewmodel.MediaViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KidsLearningVideoTheme {
                val navController = rememberNavController()
                val mediaList by viewModel.allMedia.collectAsState(initial = emptyList())

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onNavigateToVideo = { navController.navigate("video_feed") },
                            onNavigateToPhoto = { navController.navigate("photo_gallery") },
                            onNavigateToCategories = { navController.navigate("categories") },
                            onNavigateToAdd = { navController.navigate("add_media") },
                            onNavigateToSettings = { navController.navigate("settings") }
                        )
                    }
                    composable("video_feed") {
                        VideoFeedScreen(videos = mediaList, onBack = { navController.popBackStack() })
                    }
                    composable("photo_gallery") {
                        PhotoGalleryScreen(photos = mediaList, onBack = { navController.popBackStack() })
                    }
                    composable("categories") {
                        CategoriesScreen(mediaList = mediaList, onBack = { navController.popBackStack() })
                    }
                    composable("add_media") {
                        AddMediaScreen(
                            onAddMedia = { title, category, type, uri ->
                                viewModel.addMedia(title, category, type, uri)
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("settings") {
                        SettingsScreen(
                            mediaList = mediaList,
                            onDeleteMedia = { item -> viewModel.deleteMedia(item) },
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
