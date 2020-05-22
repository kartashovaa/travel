package com.kyd3snik.travel.model;

public enum Entertainment {
    CINEMA("кино"),
    RESTAURANTS("рестораны"),
    FISHING("рыбалка"),
    SEA("море"),
    HUNTING("охота"),
    EXCURSIONS("экскурсии"),
    AQUAPARK("аквапарк"),
    MUSEUMS("музеи");

    private final String title;

    Entertainment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
