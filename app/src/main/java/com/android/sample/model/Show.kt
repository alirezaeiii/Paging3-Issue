package com.android.sample.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class Show(
    @PrimaryKey val id: Long,
    val name: String,
    @Embedded val image: Image?,
    val summary: String?
)