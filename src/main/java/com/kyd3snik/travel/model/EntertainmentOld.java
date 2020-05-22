package com.kyd3snik.travel.model;

public enum EntertainmentOld {
    CINEMA("кино"),
    RESTAURANTS("рестораны"),
    FISHING("рыбалка"),
    SEA("море"),
    HUNTING("охота"),
    EXCURSIONS("экскурсии"),
    AQUAPARK("аквапарк"),
    MUSEUMS("музеи");

    private final String title;

    EntertainmentOld(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
