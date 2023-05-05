package com.example.newsapplication.singleton;

public class NewsSource {
    private static NewsSource instance;
    private String sources;

    private NewsSource() {}

    public static NewsSource getInstance() {
        if (instance == null) {
            instance = new NewsSource();
        }
        return instance;
    }

    public String getSource() {
        return sources;
    }

    public void setSource(String sources) {
        this.sources = sources;
    }
}
