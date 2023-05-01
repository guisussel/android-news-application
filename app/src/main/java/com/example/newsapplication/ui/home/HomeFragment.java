package com.example.newsapplication.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapplication.databinding.FragmentHomeBinding;
import com.example.newsapplication.ui.news.Article;
import com.example.newsapplication.ui.news.adapter.NewsAdapter;
import com.example.newsapplication.ui.news.NewsResponse;
import com.example.newsapplication.ui.news.service.NewsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    private FragmentHomeBinding binding;

    private String newsSource = "bbc-news";
    private String apiKey = "80eb8c43c5464f7b90f05e5fcdee58dd";

    private ListView listViewNews;

    NewsService newsService = new NewsService();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        swipeRefreshLayout = binding.swipeRefreshLayout;

        // Set the OnRefreshListener on the SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Refresh the data
            fetchNews();
        });

        listViewNews = binding.listViewNewsList;

        fetchNews();

        return root;
    }

    private void fetchNews() {
        Call<NewsResponse> call = newsService.getTopHeadlines(newsSource, apiKey);

        // Create a ProgressBar
        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        // Create a Dialog with the ProgressBar as the content view
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(progressBar);
        builder.setCancelable(false);
        builder.setTitle("Fething news...");
        AlertDialog dialog = builder.create();

        // Show the dialog
        dialog.show();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<Article> listOfArticles = response.body().getArticles();
                    setNewsListAdapter(listOfArticles);
                    dialog.dismiss();
                    // Notify the SwipeRefreshLayout that the refresh is complete
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    // TODO handle error case
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // TODO handle failed request
                dialog.dismiss();
                // Notify the SwipeRefreshLayout that the refresh is complete
                swipeRefreshLayout.setRefreshing(false);
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