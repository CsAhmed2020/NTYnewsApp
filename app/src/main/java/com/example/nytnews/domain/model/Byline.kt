package com.example.nytnews.domain.model

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)