package com.android.sample.model

data class Show(
    val id: Long,
    val name: String,
    val image: Image?,
    val summary: String?
)