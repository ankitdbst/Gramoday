package com.example.gramoday.data.models

data class Product(
    val cmdtyID: String,
    val cmdtySegment: String,
    val cmdtyStdName: String,
    val picUrl: String,
    val posts: List<Post>,
    val sortOrder: Int,
    val translations: Translations
)