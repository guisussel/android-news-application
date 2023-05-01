package com.example.newsapplication.ui.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey
    );
}