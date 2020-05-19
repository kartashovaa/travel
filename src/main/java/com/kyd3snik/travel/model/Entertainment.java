package com.kyd3snik.travel.model;

public enum Entertainment {
    CINEMA("кино"),
    SEA("море"),
    MOUNTAINS("горы");

    private final String title;

    Entertainment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
