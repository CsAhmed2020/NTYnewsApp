package com.example.nytnews.domain.model

data class Doc(
    val _id: String,
    val `abstract`: String,
    val byline: Byline,
    val document_type: String,
    val headline: Headline,
    val keywords: List<Keyword>,
    val lead_paragraph: String,
    val multimedia: List<Multimedia>,
    val news_desk: String,
    val pub_date: String,
    val section_name: String,
    val snippet: String,
    val source: String,
    val type_of_material: String,
    val uri: String,
    val web_url: String,
    val word_count: Int
)