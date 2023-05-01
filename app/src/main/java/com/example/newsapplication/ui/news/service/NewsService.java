package com.example.newsapplication.ui.news.service;

import com.example.newsapplication.ui.news.NewsApi;
import com.example.newsapplication.ui.news.NewsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {
    private NewsApi newsApi;

    public NewsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public Call<NewsResponse> getTopHeadlines(String sources, String apiKey) {
        return newsApi.getTopHeadlines(sources, apiKey);
    }
}
