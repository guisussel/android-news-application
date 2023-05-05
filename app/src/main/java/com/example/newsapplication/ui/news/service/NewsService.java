package com.example.newsapplication.ui.news.service;

import com.example.newsapplication.ui.news.NewsApi;
import com.example.newsapplication.ui.news.NewsResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {
    private NewsApi newsApi;

    public NewsService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public Call<NewsResponse> getTopHeadlines(String sources, String apiKey) {
        return newsApi.getTopHeadlines(sources, apiKey);
    }
}
