package com.example.newsapplication.ui.sources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.newsapplication.R;
import com.example.newsapplication.databinding.FragmentSourcesBinding;
import com.example.newsapplication.singleton.NewsSource;

public class SourcesFragment extends Fragment {

    private FragmentSourcesBinding binding;

    private Button buttonBbcNews;

    private Button buttonCnnNews;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SourcesViewModel sourcesViewModel =
                new ViewModelProvider(this).get(SourcesViewModel.class);

        binding = FragmentSourcesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonBbcNews = binding.buttonBbcNews;

        buttonBbcNews.setOnClickListener(view -> {
            NewsSource.getInstance().setSource(SourcesEnum.Source.BBC_NEWS.getValue());
            navigateToHomeScreen();
        });

        buttonCnnNews = binding.buttonCnnNews;

        buttonCnnNews.setOnClickListener(view -> {
            NewsSource.getInstance().setSource(SourcesEnum.Source.CNN.getValue());
            navigateToHomeScreen();
        });


        return root;
    }

    private void navigateToHomeScreen() {
        NavHostFragment.findNavController(SourcesFragment.this)
                .navigate(R.id.navigation_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}