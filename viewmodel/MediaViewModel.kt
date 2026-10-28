package com.kids.learningvideo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kids.learningvideo.KidsApp
import com.kids.learningvideo.data.MediaEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MediaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as KidsApp).repository

    val allMedia: StateFlow<List<MediaEntity>> = repository.allMedia
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addMedia(title: String, category: String, type: String, uriString: String) {
        viewModelScope.launch {
            repository.insert(MediaEntity(title = title, category = category, type = type, uriString = uriString))
        }
    }

    fun deleteMedia(media: MediaEntity) {
        viewModelScope.launch {
            repository.delete(media)
        }
    }

    fun updateTitle(id: Long, newTitle: String) {
        viewModelScope.launch {
            repository.updateTitle(id, newTitle)
        }
    }
}
