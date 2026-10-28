package com.kids.learningvideo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String, // English Learning, Arabic Learning, বাংলা শিক্ষা, গণিত শিক্ষা, ইসলামিক শিক্ষা, অন্যান্য
    val type: String,     // "VIDEO" or "PHOTO"
    val uriString: String, // Persistent content URI or absolute path
    val timestamp: Long = System.currentTimeMillis()
)
