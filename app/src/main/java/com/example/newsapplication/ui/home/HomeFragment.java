package com.example.newsapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapplication.R;
import com.example.newsapplication.databinding.FragmentHomeBinding;
import com.example.newsapplication.singleton.NewsSource;
import com.example.newsapplication.ui.news.Article;
import com.example.newsapplication.ui.news.NewsResponse;
import com.example.newsapplication.ui.news.adapter.NewsAdapter;
import com.example.newsapplication.ui.news.service.NewsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    private FragmentHomeBinding binding;

    private String newsSource = NewsSource.getInstance().getSource();
    private String apiKey;

    private ListView listViewNews;

    private TextView textViewErrorNote;

    NewsService newsService = new NewsService();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        swipeRefreshLayout = binding.swipeRefreshLayout;

        swipeRefreshLayout.setOnRefreshListener(this::fetchNews);

        listViewNews = binding.listViewNewsList;

        textViewErrorNote = binding.textViewErrorNote;

        fetchNews();

        return root;
    }

    private void fetchNews() {
        apiKey = readApiKey();
        Call<NewsResponse> call = newsService.getTopHeadlines(newsSource, apiKey);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    List<Article> listOfArticles = response.body().getArticles();
                    setNewsListAdapter(listOfArticles);
                    swipeRefreshLayout.setRefreshing(false);
                    textViewErrorNote.setVisibility(View.GONE);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    textViewErrorNote.setVisibility(View.VISIBLE);
                    if (response.code() == 401) {
                        textViewErrorNote.setText(R.string.unexpected_error_fetch_news);
                        textViewErrorNote.append(getResources().getString(R.string.error_401));
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                textViewErrorNote.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setNewsListAdapter(List<Article> articles) {
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), articles);
        listViewNews.setAdapter(newsAdapter);
    }

    private String readApiKey() {
        try (InputStream input = getActivity().getAssets().open("keys.properties")) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty("API_KEY");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(NewsSource.getInstance().getSource().toUpperCase(Locale.ROOT));
    }

}
