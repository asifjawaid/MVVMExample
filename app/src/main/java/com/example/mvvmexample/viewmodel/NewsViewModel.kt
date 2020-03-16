package com.example.mvvmexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.model.NewsResponseModel
import com.example.mvvmexample.repository.NewsRepository


class NewsViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<NewsResponseModel?>? = null
    private var newsRepository: NewsRepository? = null
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = NewsRepository.instance
        mutableLiveData = newsRepository!!.getNews("techcrunch", "af20cbeb5e4c4431b48e7bdb068827bf")
    }

    fun getNewsRepository(): LiveData<NewsResponseModel?>? {
        return mutableLiveData
    }
}