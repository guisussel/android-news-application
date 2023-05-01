package com.example.newsapplication.ui.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapplication.R;
import com.example.newsapplication.ui.news.Article;
import com.example.newsapplication.util.DateFormatterUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<Article> {

    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = inflater.inflate(R.layout.list_articles, parent, false);
        }

        Article article = getItem(position);

        TextView titleTextView = itemView.findViewById(R.id.textViewNewsTitle);
        titleTextView.setText(article.getTitle());

        TextView descriptionTextView = itemView.findViewById(R.id.textViewNewsDescription);
        descriptionTextView.setText(article.getDescription());

        ImageView imageViewNewsImage = itemView.findViewById(R.id.imageViewNewsImage);
        Picasso.get().load(article.getUrlToImage()).into(imageViewNewsImage);

        TextView textVewNewsDate = itemView.findViewById(R.id.textVewNewsDate);
        textVewNewsDate.setText(DateFormatterUtil.format(article.getPublishedAt()));

        itemView.setOnClickListener(view -> {
            Toast.makeText(this.getContext(), "Clicked item:  " + article.getTitle(), Toast.LENGTH_SHORT).show();
        });

        return itemView;
    }
}
