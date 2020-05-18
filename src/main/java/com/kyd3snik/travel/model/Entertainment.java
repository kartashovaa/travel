package com.kyd3snik.travel.model;

public enum Entertainment {
    CINEMA("кино"),
    SEA("море"),
    MOUNTAINS("горы");

    private String title;

    Entertainment(String title) {
        this.title = title;
    }
}
