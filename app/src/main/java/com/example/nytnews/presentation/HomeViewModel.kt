package com.example.nytnews.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nytnews.MyApplication
import com.example.nytnews.data.Resource
import com.example.nytnews.domain.model.Doc
import com.example.nytnews.domain.model.NewsResponse
import com.example.nytnews.domain.model.Response
import com.example.nytnews.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: NewsRepository

    private var _newsResponse = MutableLiveData<Resource<NewsResponse>>()
    val newsResponse : LiveData<Resource<NewsResponse>> get() = _newsResponse

    lateinit var selectedArticle : Doc

    init {
        (application as MyApplication).getAppComponent().inject(this)
        getNews()
    }

    private fun getNews(){
        var news: List<Doc>
        viewModelScope.launch {
            repository.getArticles()
                .collect { result ->
                    when(result){
                        is Resource.Success -> {
                           news = result.data!!.response.docs.filter { doc ->
                                doc.type_of_material == "News"
                            }
                            _newsResponse.value = Resource.Success(
                                data = NewsResponse(
                                    copyright = result.data.copyright,
                                    response = Response(
                                        docs = news,
                                        meta = result.data.response.meta
                                    )
                                )
                            )
                        }
                        else -> {
                            _newsResponse.value = result
                        }
                    }

                }
        }
    }

    fun updateSelectedArticle(doc: Doc){
        selectedArticle =doc
    }
}