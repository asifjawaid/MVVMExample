package com.example.mvvmexample.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmexample.model.NewsResponseModel
import com.example.mvvmexample.retrofit.NewsApi
import com.example.mvvmexample.retrofit.RetrofitService.cteateService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository {
    private val newsApi: NewsApi = cteateService(NewsApi::class.java)
    fun getNews(source: String?, key: String?): MutableLiveData<NewsResponseModel?> {
        val newsData: MutableLiveData<NewsResponseModel?> = MutableLiveData<NewsResponseModel?>()
        newsApi.getNewsList(source, key)!!.enqueue(object : Callback<NewsResponseModel?> {
            override fun onResponse(call: Call<NewsResponseModel?>?, response: Response<NewsResponseModel?>) {
                if (response.isSuccessful) {
                    newsData.value = response.body()
                }
            }

            override fun onFailure(call: Call<NewsResponseModel?>?, t: Throwable?) {
                newsData.value = null
            }
        })
        return newsData
    }

    companion object {
        private var newsRepository: NewsRepository? = null
        val instance: NewsRepository?
            get() {
                if (newsRepository == null) {
                    newsRepository = NewsRepository()
                }
                return newsRepository
            }
    }

}