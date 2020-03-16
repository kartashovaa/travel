package com.kyd3snik.travel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private Country country;
    private List<Entertainment> entertainments;

    public City(String title, Country country, List<Entertainment> entertainments) {
        this.title = title;
        this.country = country;
        this.entertainments = entertainments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Entertainment> getEntertainments() {
        return entertainments;
    }

    public void setEntertainments(List<Entertainment> entertainments) {
        this.entertainments = entertainments;
    }

    public void addEntertainment(Entertainment entertainment) {
        entertainments.add(entertainment);
    }

    public void removeEntertainment(Entertainment entertainment) {
        entertainments.remove(entertainment);
    }
}
