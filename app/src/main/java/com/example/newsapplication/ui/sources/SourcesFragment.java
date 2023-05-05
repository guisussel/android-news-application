package com.example.newsapplication.ui.sources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapplication.databinding.FragmentSourcesBinding;

public class SourcesFragment extends Fragment {

    private FragmentSourcesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SourcesViewModel sourcesViewModel =
                new ViewModelProvider(this).get(SourcesViewModel.class);

        binding = FragmentSourcesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}