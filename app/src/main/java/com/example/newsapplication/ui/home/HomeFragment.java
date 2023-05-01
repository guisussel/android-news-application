package com.example.newsapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.newsapplication.ui.news.Article;
import com.example.newsapplication.ui.news.adapter.NewsAdapter;
import com.example.newsapplication.ui.news.NewsResponse;
import com.example.newsapplication.ui.news.service.NewsService;
import com.example.newsapplication.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private String newsSource = "bbc-news";
    private String apiKey = "";

    private ListView listViewNews;

    NewsService newsService = new NewsService();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewNews = binding.listViewNewsList;

        fetchNews();

        return root;
    }

    private void fetchNews() {
        Call<NewsResponse> call = newsService.getTopHeadlines(newsSource, apiKey);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<Article> listOfArticles = response.body().getArticles();
                    setNewsListAdapter(listOfArticles);
                } else {
                    // TODO handle error case
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // TODO handle failed request
            }
        });
    }

    private void setNewsListAdapter(List<Article> articles) {
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), articles);
        listViewNews.setAdapter(newsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}