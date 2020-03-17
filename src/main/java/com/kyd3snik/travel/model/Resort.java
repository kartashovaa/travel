package com.kyd3snik.travel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


// Курорт
@Entity
public class Resort {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<City> path;
    private int durationInDays;
    private Date startDate;
    private Date endDate;

    public Resort() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<City> getPath() {
        return path;
    }

    public void setPath(List<City> path) {
        this.path = path;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
