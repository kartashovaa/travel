package com.kyd3snik.travel.model;

//удобства
//TODO: добавить нормальные имена
public enum Facility {
    SHOWER_IN_HOTEL_ROOM("душ в номере"),
    WC_IN_HOTEL_ROOM("туалет в номере"),
    KITCHEN("кухня");

    private String title;

    Facility(String title) {
        this.title = title;
    }
}
