package com.example.nytnews.domain.repository

import com.example.nytnews.data.Resource
import com.example.nytnews.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getArticles() : Flow<Resource<NewsResponse>>
}