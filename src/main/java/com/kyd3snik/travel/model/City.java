package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne
    private Country country;
    @Enumerated
    @ElementCollection
    private List<Entertainment> entertainments;

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
