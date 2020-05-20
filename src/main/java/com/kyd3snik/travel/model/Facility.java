package com.kyd3snik.travel.model;

public enum Facility {
    SHOWER_IN_HOTEL_ROOM("душ в номере"),
    WC_IN_HOTEL_ROOM("туалет в номере"),
    KITCHEN("кухня"),
    AIR_CONDITIONING("кондиционер"),
    WI_FI("Wi-Fi"),
    SWIMMING_POOL("бассейн");

    private final String title;

    Facility(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
