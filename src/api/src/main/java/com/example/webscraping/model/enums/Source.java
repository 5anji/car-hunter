package com.example.webscraping.model.enums;

public enum Source {
    AUTOBID("autobid.de");
    public final String url;
    Source(String url) {
        this.url = url;
    }
}
