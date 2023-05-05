package com.example.newsapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapplication.ui.news.Article;
import com.example.newsapplication.util.DateFormatterUtil;
import com.squareup.picasso.Picasso;

public class ArticleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Article article = (Article) bundle.getSerializable("article");

            setTitle(article.getAuthor());

            ImageView imageViewNewsImage = findViewById(R.id.imageViewNewsImage);
            Picasso.get().load(article.getUrlToImage()).into(imageViewNewsImage);

            TextView textViewNewsTitle = findViewById(R.id.textViewNewsTitle);
            textViewNewsTitle.setText(article.getTitle());

            TextView textVewNewsDate = findViewById(R.id.textVewNewsDate);
            textVewNewsDate.setText(DateFormatterUtil.format(article.getPublishedAt()));

            TextView textViewNewsDescription = findViewById(R.id.textViewNewsDescription);
            textViewNewsDescription.setText(article.getDescription());

            TextView textViewNewsContent = findViewById(R.id.textViewNewsContent);
            if (article.getContent().contains("[+")) {
                textViewNewsContent.setText(article.getContent().substring(0, article.getContent().indexOf("[+")));
            } else {
                textViewNewsContent.setText(article.getContent());
            }

            Button buttonReadMore = findViewById(R.id.buttonReadMore);
            buttonReadMore.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(intent);
            });
        }

    }

}