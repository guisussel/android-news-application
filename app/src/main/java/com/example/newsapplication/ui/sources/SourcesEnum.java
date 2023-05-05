package com.example.newsapplication.ui.sources;

public class SourcesEnum {

    public enum Source {
        BBC_NEWS("bbc-news"),
        CNN("cnn"),;

        private final String value;

        Source(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
