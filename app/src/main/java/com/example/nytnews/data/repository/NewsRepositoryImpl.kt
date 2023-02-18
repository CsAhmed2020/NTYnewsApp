package com.example.nytnews.data.repository

import android.util.Log
import com.example.nytnews.data.Resource
import com.example.nytnews.data.remote.NewsApi
import com.example.nytnews.domain.model.NewsResponse
import com.example.nytnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class NewsRepositoryImpl(
    private val api : NewsApi
) : NewsRepository {

    override suspend fun getArticles(): Flow<Resource<NewsResponse>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getNews()
                emit(Resource.Success(data = result))
            }catch (throwable: Throwable) {
                Log.e("Ahmed throwable->","$throwable")
                when (throwable) {
                    is HttpException -> {
                        Log.e("Ahmed HttpException->","${throwable.response()!!.errorBody()}")
                        Resource.Error(message = "${throwable.code()} , ${throwable.message()}", data = null)
                    }
                    else -> {
                        Log.e("Ahmed No http error->","${throwable.message}")
                        Resource.Error(message = throwable.message.toString())
                    }
                }
            }
        }
    }
}