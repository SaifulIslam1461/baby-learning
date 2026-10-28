package com.kids.learningvideo.data

import android.app.Application
import kotlinx.coroutines.flow.Flow

class MediaRepository(private val mediaDao: MediaDao) {
    val allMedia: Flow<List<MediaEntity>> = mediaDao.getAllMedia()

    fun getMediaByType(type: String): Flow<List<MediaEntity>> = mediaDao.getMediaByType(type)

    suspend fun insert(media: MediaEntity) {
        mediaDao.insertMedia(media)
    }

    suspend fun delete(media: MediaEntity) {
        mediaDao.deleteMedia(media)
    }

    suspend fun updateTitle(id: Long, title: String) {
        mediaDao.updateTitle(id, title)
    }
}

class KidsApp : android.app.Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { MediaRepository(database.mediaDao()) }
}
