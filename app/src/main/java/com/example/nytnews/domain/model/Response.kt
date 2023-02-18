package com.example.nytnews.domain.model

import com.example.nytnews.domain.model.Doc
import com.example.nytnews.domain.model.Meta

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)