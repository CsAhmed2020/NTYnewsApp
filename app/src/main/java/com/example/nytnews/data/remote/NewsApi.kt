package com.example.nytnews.data.remote

import com.example.nytnews.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("2018/11.json")
    suspend fun getNews(
        @Query("api-key") apiKey: String = "HCnuvB0r9Fs4oo2sN9EcebITVy7wyc2D"
    ): NewsResponse

}